package com.example.makhzan.Service;

import com.example.makhzan.Api.ApiException;
import com.example.makhzan.DTO.CustomerDTO;
import com.example.makhzan.Model.*;
import com.example.makhzan.Repository.CustomerRepository;
import com.example.makhzan.Repository.MediaRepository;
import com.example.makhzan.Repository.OrdersRepository;
import com.example.makhzan.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final OrdersRepository ordersRepository;
    private final MediaRepository mediaRepository;
    private final OrdersService ordersService;

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public void register(CustomerDTO customerDTO){
        User user=new User(null,customerDTO.getPassword(),customerDTO.getName(),customerDTO.getEmail(),customerDTO.getPhoneNumber(),customerDTO.getRole(),null,null);
        user.setRole("CUSTOMER");
        //String hash=new BCryptPasswordEncoder().encode(user.getPassword());
        //user.setPassword(hash);
        userRepository.save(user);

        Customer customer=new Customer(null, customerDTO.getBirthDate(),null,user,null,null);
        customerRepository.save(customer);
    }
    public void updateCustomer(CustomerDTO customerDTO ,Integer id) {
        Customer oldCustomer = customerRepository.findCustomerById(id);
        if (oldCustomer == null) {
            throw new ApiException("Customer Not Found");
        }
        oldCustomer.setName(customerDTO.getName());
        oldCustomer.setEmail(customerDTO.getEmail());
        oldCustomer.setPhoneNumber(customerDTO.getPhoneNumber());
        oldCustomer.setBirthDate(customerDTO.getBirthDate());
        //String hash=new BCryptPasswordEncoder().encode(customerDTO.getPassword());
        //oldCustomer.setPassword(hash);

        customerRepository.save(oldCustomer);
    }
    public void deleteCustomer(Integer id){
        Customer customer = customerRepository.findCustomerById(id);
        if (customer == null) {
            throw new ApiException("Customer Not Found");
        }
        User user=userRepository.findUserById(id);
        if (user == null) {
            throw new ApiException("User Not Found");
        }
        for(Orders o:ordersRepository.findOrdersByCustomer(customer)){
            o.setCustomer(null);
            ordersService.deleteOrder(o.getId(),o.getCustomer().getId()); //تأكدي من اليوزر اي دي
//            o.deleteorder احذفي الاوردر

        }
        customer.setOrders(null);

        customer.setUser(null);
        user.setCustomer(null);

        userRepository.delete(user);
        customerRepository.delete(customer);
    }

    //شيلي تاريخ البداية و النهاية
    public ArrayList<ArrayList<String>> getMyOrders(Integer customerId){
        Customer customer=customerRepository.findCustomerById(customerId);
        if(customer==null){
            throw new ApiException("Customer Not Found");
        }
        ArrayList<ArrayList<String>> Orderss = new ArrayList<>();
        for (Orders orders : customer.getOrders()) {
            {
                ArrayList<String> orderInfo = new ArrayList<>();
                orderInfo.add("order id :" + orders.getId());
                orderInfo.add("totalPrice :" + orders.getTotalPrice() + "sar");
                orderInfo.add("startDate :" + orders.getStartDate());
                orderInfo.add("endDate :" + orders.getEndDate());
                orderInfo.add("status :" + orders.getStatus());
                orderInfo.add("orderDate :" + orders.getOrderDate());
                orderInfo.add(orders.getStorage().getName());
                Media m=mediaRepository.findMediaByTypeImage("IMAGE");
                orderInfo.add(m.getUrl());
                Orderss.add(orderInfo);
            }
        }
        return Orderss;
    }

    public List<Customer> findsCustomersByRentTimes(){
        return customerRepository.findCustomerByRentTimesDesc();
    }

    public List<Orders> findOrdersByStatus(String status){
        return customerRepository.findAllByOrderStatus(status);
    }

}
