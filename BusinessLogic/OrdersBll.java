package BusinessLogic;

import java.util.List;
import java.util.NoSuchElementException;

import Model.Orders;
import DataAccess.OrdersDAO;
import Model.Orders;


public class OrdersBll {
    private OrdersDAO order;

    public OrdersBll(){
        this.order = new OrdersDAO();
    }
    public Orders findById(int id){
        Orders order1 = order.findById(id);
        if(order1 == null){
            throw new NoSuchElementException("The order with id =" + id + " was not found!");
        }
        return order1;
    }

    public void insert(Orders order1){
        order.insert(order1);
    }

    public void update(Orders order1){
        order.update(order1);
    }

    public void delete(Orders order1){
        order.delete(order1);
    }

}
