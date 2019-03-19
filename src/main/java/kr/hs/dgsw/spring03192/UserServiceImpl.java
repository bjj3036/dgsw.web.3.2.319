package kr.hs.dgsw.spring03192;

import org.springframework.stereotype.Service;

import javax.validation.constraints.AssertTrue;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    List<User> userList;

    public UserServiceImpl() {
        this.userList = new ArrayList<User>();
    }

    @Override
    public List<User> list() {
        return this.userList;
    }

    @Override
    public User view(String id) { //Java 8+
        User found = this.userList.stream()
                .filter(user -> user.getId().equals(id))
                .findAny()
                .orElse(null);
        return found;
    }

    @Override
    public boolean add(User user) {
        User found = view(user.getId());
        if(found==null)
            return userList.add(user);
        return false;
    }

    @Override
    public User update(User user) {
        User found = view(user.getId());
        if(found!=null)
        {
            found.setName(user.getName());
            found.setEmail(user.getEmail());
        }
        return found;
    }

    @Override
    public boolean delete(String id) {
        User found = view(id);
        return userList.remove(found);
    }

    //다양한 검색 방법들
    public User find1(String name) {
        Iterator<User> iterator = userList.iterator();
        while (iterator.hasNext()) {
            User found = iterator.next();
            if (found.getName().equals(name))
                return found;
        }
        return null;
    }

    public User find2(String name) { //Java 5+
        for (User found : userList) {
            if (found.getName().equals(name))
                return found;
        }
        return null;
    }

    public User find3(String name) {
        for (int i = 0; i < userList.size(); i++) {
            User found = userList.get(i);
            if (found.getName().equals(name))
                return found;
        }
        return null;
    }
}
