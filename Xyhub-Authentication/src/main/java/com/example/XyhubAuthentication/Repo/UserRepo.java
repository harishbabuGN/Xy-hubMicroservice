//package com.example.XyhubAuthentication.Repo;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import com.example.XyhubAuthentication.model.XyhubUserDetails;
//
//
//public interface UserRepo extends JpaRepository<XyhubUserDetails, String> {
//	
////	@Query("SELECT u FROM User u WHERE LOWER(u.username) = LOWER(:username)")
////	XyhubUserDetails loadUserByUsername(@Param("username") String username);
//	
//	@Query("SELECT u FROM XyhubUserDetails u WHERE u.username =:username")
//	XyhubUserDetails loadUserByUsername(@Param("username") String username);
//
//}
