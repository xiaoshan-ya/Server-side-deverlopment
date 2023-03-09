package tacos.data.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import tacos.data.OrderRepository;

@Service
public class OrderAdminService { //业务层

	private OrderRepository orderRepository;

	public OrderAdminService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@PreAuthorize("hasRole('ADMIN')") //确定鉴权，且当前用户权限为ADMIT，方法级别的鉴权
	public void deleteAllOrders() {
		orderRepository.deleteAll();
	}

}
