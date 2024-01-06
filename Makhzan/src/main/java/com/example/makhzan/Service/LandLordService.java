package com.example.makhzan.Service;

import com.example.makhzan.Api.ApiException;
import com.example.makhzan.DTO.LandlordDTO;
import com.example.makhzan.Model.*;
import com.example.makhzan.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LandLordService {
    private final LandLordRepository landLordRepository;
    private final UserRepository userRepository;
    private final StorageRepository storageRepository;
    private final MediaRepository mediaRepository;
    private final OrdersRepository ordersRepository;
    public List<Landlord> getAllLandlords(){
        return landLordRepository.findAll();
    }
    public void register(LandlordDTO landlordDTO){
        User user=new User(null,landlordDTO.getPassword(),landlordDTO.getName(),landlordDTO.getEmail(),landlordDTO.getPhoneNumber(),landlordDTO.getRole(),null,null); //r
        user.setRole("LANDLORD");
       // String hash=new BCryptPasswordEncoder().encode(user.getPassword());
        //user.setPassword(hash);
        userRepository.save(user);

        Landlord landlord=new Landlord(null, "pending",landlordDTO.getIsCompany(),landlordDTO.getLicense(),user,null);
        landLordRepository.save(landlord);
    }

    public void updateLandlord(LandlordDTO landlordDTO ,Integer id) {
        Landlord landlord = landLordRepository.findLandLordById(id);
        if (landlord == null) {
            throw new ApiException("Landlord Not Found");
        }

        landlord.setName(landlordDTO.getName());
        landlord.setEmail(landlordDTO.getEmail());
        landlord.setPhoneNumber(landlordDTO.getPhoneNumber());
        landlord.setIsCompany(landlordDTO.getIsCompany());
        landlord.setLicense(landlordDTO.getLicense());
        //String hash=new BCryptPasswordEncoder().encode(landlordDTO.getPassword());
        //landlord.setPassword(hash);

        landLordRepository.save(landlord);
    }

    public void deleteLandlord(Integer id){
        Landlord landlord = landLordRepository.findLandLordById(id);
        if (landlord == null) {
            throw new ApiException("Landlord Not Found");
        }
        User user=userRepository.findUserById(id);
        if (user == null) {
            throw new ApiException("User Not Found");
        }
        user.setLandlord(null);
        userRepository.save(user);
        for(Storage s:storageRepository.findStorageByLandlord(landlord)){
            s.setLandlord(null);
            s.setOrders(null);
            s.setMedias(null);
            storageRepository.save(s);
            //            s.deleteorder احذفي الستورج استدعي الداله
        }
        landlord.setUser(null);
        user.setLandlord(null);
        userRepository.save(user);
        landLordRepository.save(landlord);
        userRepository.delete(user);
        landLordRepository.delete(landlord);
    }

    ///
    public ArrayList<ArrayList<String>> ordersForStorage(Integer storageId){
        Storage storage=storageRepository.findStorageById(storageId);
        ArrayList<ArrayList<String>> Orderss = new ArrayList<>();
        for (Orders orders : storage.getOrders()) {
             {
                ArrayList<String> orderInfo = new ArrayList<>();
                orderInfo.add("order id :" + orders.getId());
                orderInfo.add("totalPrice :" + orders.getTotalPrice() + "sar");
                orderInfo.add("startDate :" + orders.getStartDate());
                orderInfo.add("endDate :" + orders.getEndDate());
                orderInfo.add("status :" + orders.getStatus());
                orderInfo.add("orderDate :" + orders.getOrderDate());
                orderInfo.add(orders.getStorage().getName());
                orderInfo.add("user name :" + orders.getCustomer().getName());
                 Media m=mediaRepository.findMediaByTypeImage("IMAGE");
                orderInfo.add(m.getUrl());
                Orderss.add(orderInfo);
            }
        }
        return Orderss;
    }


      ////
    public void acceptOrder(Integer storageId, Integer orderId) {
        Orders orders=ordersRepository.findOrdersByStorageIdAndOrderId(storageId, orderId);
        if (orders==null) {
            throw new ApiException("Order Not Found");
        }
        List<Orders> orderList=ordersRepository.findOrdersByStorageId(storageId);
        for(Orders o:orderList){
            if(o.getId().equals(orderId)){
                if(o.getStatus().equals("ACCEPTED")){
                    throw new ApiException("You Have Already Accepted This Order!");
                }
                if(o.getStorage().equals(false)){
                    throw new ApiException("This Storage Already Rented");
                }
                o.getStorage().setAvailable(false);
                o.setStatus("ACCEPTED");
                ordersRepository.save(o);
            }
            else if(o.getStartDate().isBefore(orders.getEndDate())||o.getEndDate().isAfter(orders.getStartDate())){
                o.setStatus("REJECT");
                ordersRepository.save(o);
            }
        }
    }



   // Reject
    public void rejectOrder(Integer storageId, Integer orderId) {
        Orders orders=ordersRepository.findOrdersByStorageIdAndOrderId(storageId, orderId);
        if (orders==null) {
            throw new ApiException("Order Not Found");
        }
        orders.setStatus("REJECT");
        ordersRepository.save(orders);
    }

    //endPeriod

    public void endPeriod(Integer storageId){
        Storage storage=storageRepository.findStorageById(storageId);
        if(storage==null){
            throw new ApiException("Storage Not Found");
        }
        storage.setAvailable(true);
        for(Orders o:storage.getOrders()) {
                if (o.getStatus().equals("ACCEPTED")) {
                   o.setStatus("COMPLETED");
                   ordersRepository.save(o);
                }
        }
        storageRepository.save(storage);
    }
}
