package com.Startup.Spring.Boot.Repository;

import com.Startup.Spring.Boot.Entity.JournalEntry;
import com.Startup.Spring.Boot.Entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByUserName(String name);
    void deleteByUserName(String name);
}
