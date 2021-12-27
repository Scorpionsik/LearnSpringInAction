package io.github.scorpionsik.learn.LearnSpringInAction.repositiries;

import io.github.scorpionsik.learn.LearnSpringInAction.components.IngredientRef;
import io.github.scorpionsik.learn.LearnSpringInAction.models.Shawarma;
import io.github.scorpionsik.learn.LearnSpringInAction.models.ShawarmaOrder;
import io.github.scorpionsik.learn.LearnSpringInAction.repositiries.i.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Repository
public class JdbcOrderRepository implements OrderRepository {
    private final JdbcOperations jdbcOperations;

    @Autowired
    public JdbcOrderRepository(JdbcOperations jdbcOperations){
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    @Transactional
    public ShawarmaOrder save(ShawarmaOrder shawarmaOrder) {
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
            "insert into Shawarma_Order " +
            "(delivery_name, delivery_street, delivery_city, delivery_state, delivery_zip, cc_number, cc_expiration, cc_cvv, placed_at) " +
                    "values (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP
                );
        pscf.setReturnGeneratedKeys(true);

        shawarmaOrder.setPlacedAt(new Date());
        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(Arrays.asList(
           shawarmaOrder.getDeliveryName(),
           shawarmaOrder.getDeliveryStreet(),
           shawarmaOrder.getDeliveryCity(),
           shawarmaOrder.getDeliveryState(),
           shawarmaOrder.getDeliveryZip(),
           shawarmaOrder.getCcNumber(),
           shawarmaOrder.getCcExpiration(),
           shawarmaOrder.getCcCVV(),
           shawarmaOrder.getPlacedAt()
        ));
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(psc, keyHolder);
        long orderId = keyHolder.getKey().longValue();
        shawarmaOrder.setId(orderId);

        List<Shawarma> shawarmas = shawarmaOrder.getShawarmas();
        int i = 0;
        for(Shawarma shawarma : shawarmas){
            saveShawarma(orderId, i++, shawarma);
        }
        return shawarmaOrder;
    }

    private long saveShawarma(Long orderId, int orderKey, Shawarma shawarma){
        shawarma.setCreateAt(new Date());
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory("insert into Shawarma (name, created_at, taco_order, taco_order_key) " +
                "values (?, ?, ?, ?)",
                Types.VARCHAR, Types.TIMESTAMP, Types.BIGINT, Types.BIGINT);
        pscf.setReturnGeneratedKeys(true);
        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(Arrays.asList(
           shawarma.getName(),
           shawarma.getCreateAt(),
           orderId,
           orderKey
        ));
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(psc, keyHolder);
        long shawarmaId = keyHolder.getKey().longValue();
        shawarma.setId(shawarmaId);
        saveIngredientRef(shawarmaId, shawarma.getIngredients());
        return shawarmaId;
    }

    private void saveIngredientRef(Long shawarmaId, List<IngredientRef> ingredients){
        int key = 0;
        for(IngredientRef ingredient : ingredients){
            jdbcOperations.update("insert into Ingredient_Ref (ingredient, taco, taco_key) values (?, ?, ?)",
                    ingredient.getIngredient(),
                    shawarmaId,
                    key++);
        }
    }
}
