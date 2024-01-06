package com.example.makhzan.Repository;

import com.example.makhzan.Model.Customer;
import com.example.makhzan.Model.Landlord;
import com.example.makhzan.Model.Orders;
import com.example.makhzan.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders,Integer> {
    Orders findOrdersById(Integer id);

    @Query("select o from Orders o where o.storage.id =?1")
    List<Orders> getOrders(Integer storageid);

    List<Orders> findOrdersByCustomer(Customer customer);

    @Query("select o from Orders o where o.storage.id=?1 and o.id=?2")
    Orders findOrdersByStorageIdAndOrderId(Integer storageId,Integer orderId);

    @Query("select o from Orders o where o.storage.id=?1")
    List<Orders> findOrdersByStorageId(Integer storageId);


}
