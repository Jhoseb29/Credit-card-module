package org.jala.university.repository;

import org.jala.university.model.RecordModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RecordTableRepository extends JpaRepository<RecordModel, UUID> {
}
