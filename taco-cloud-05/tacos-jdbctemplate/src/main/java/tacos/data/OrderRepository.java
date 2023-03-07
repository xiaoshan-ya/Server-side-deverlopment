package tacos.data;

import java.util.Optional;

import tacos.TacoOrder;

//业务层到Dao层一定要有接口
public interface OrderRepository {//用于存取order

  TacoOrder save(TacoOrder order);

  Optional<TacoOrder> findById(Long id);

}
