package com.horizon.accomplishment.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.horizon.accomplishment.models.Bucket;

@Repository
public interface BucketRepo extends CrudRepository<Bucket, Long> {
	List<Bucket> findAll();

}
