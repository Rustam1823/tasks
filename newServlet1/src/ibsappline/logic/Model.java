package ibsappline.logic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Model implements Serializable {
    private static final Model instance = new Model();

    private final Map<Integer,User> model;

    public static Model getInstance(){
        return instance;
    }

    private Model(){
        model = new HashMap<>();

        model.put(1,new User("Henry","Chinaski",50000));
        model.put(2,new User("Otto","FonBismark",10000));
        model.put(3,new User("Howard","Rork",30000));
    }

    public void add(User user,int id){
        model.put(id,user);
    }

    public void delete(int id){
        model.remove(id);
    }

    public Map<Integer,User> getFromList(){
        return model;
    }

}
