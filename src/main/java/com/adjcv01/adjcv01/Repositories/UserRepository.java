package com.adjcv01.adjcv01.Repositories;
/*
import com.adjcv01.adjcv01.Models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
*/
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.adjcv01.adjcv01.Models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>  {
    public Optional<User> findByUsername(String username);

}