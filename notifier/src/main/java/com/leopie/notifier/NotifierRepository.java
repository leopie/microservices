package com.leopie.notifier;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NotifierRepository extends JpaRepository<Notification, Integer> {
}
