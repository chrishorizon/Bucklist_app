package com.horizon.accomplishment.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.horizon.accomplishment.models.Bucket;
import com.horizon.accomplishment.repositories.BucketRepo;

@Service
public class BucketService {
	
	@Autowired
	private BucketRepo repo;
	
    // return all
	public List<Bucket> allBuckets(){
		return repo.findAll();
	}

    // retrive one
	public Bucket oneBucket(Long id) {
		Optional<Bucket> optB = repo.findById(id);
		if(optB.isPresent()) {
			return optB.get();
		} else return null;
	}

    // create
	public Bucket createBucket(Bucket b) {
		return repo.save(b);
	}

    // update one by ID

    // delete one by ID
	public void deleteBucket(Long id) {
		repo.deleteById(id);
	}

}
