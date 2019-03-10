package uz.dukon.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.dukon.controllers.model.OrderDto;
import uz.dukon.entities.OrderEntity;
import uz.dukon.entities.TransactionEntity;
import uz.dukon.repository.OrderRepository;
import uz.dukon.repository.TransactionRepository;
import uz.dukon.service.OrderService;

import java.util.Date;

/**
 * Created by Jack on 20.02.2019.
 */
@Service
public class OrderServiceImpl implements OrderService
{
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private TransactionRepository transactionRepository;



    @Override
    public void save(OrderDto orderDto)
    {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setCash(orderDto.getCashSum());
        orderEntity.setPlastic(orderDto.getPcardSuma());
        orderEntity.setTotalSumm(orderDto.getTotalAmount());
        orderEntity.setCreateDate(new Date());
        orderRepository.save(orderEntity);
        Long id = orderRepository.getLastCreated();
        orderDto.getList().forEach(transactionDto -> {
            TransactionEntity transactionEntity = new TransactionEntity();
            transactionEntity.setOrderId(id);
            transactionEntity.setDeleted(false);
            transactionEntity.setProductId(transactionDto.getProductId());
            transactionEntity.setProductTotalSoldPrice(transactionDto.getProductTotalSellPrice());
            transactionEntity.setProductCount(transactionDto.getProductCount());
            transactionEntity.setProductPrice(transactionDto.getProductPrice());
            transactionEntity.setCreateDate(new Date());
             transactionRepository.save(transactionEntity);
        });

    }


}
