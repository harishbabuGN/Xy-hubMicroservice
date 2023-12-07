package com.example.XyhubEmployee.Service;

import org.springframework.data.domain.Pageable;

import com.example.XyhubEmployee.Entity.Notifications;
import com.example.XyhubEmployee.apiresponses.ApiResponse;

public interface NotificationService {

	//Page<Notification> getActiveNotifications(Pageable pageable);

//	ApiResponse getAllNotifications(Pageable pageable);
//	
//	ApiResponse getNotificationCount(); 
//	
//	ApiResponse clearAllNotifications();

	Notifications createNotification(Notifications notification);

}
