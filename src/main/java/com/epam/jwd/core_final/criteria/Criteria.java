package com.epam.jwd.core_final.criteria;

import java.util.stream.Stream;

import com.epam.jwd.core_final.domain.BaseEntity;

/**
 * Should be a builder for {@link BaseEntity} fields
 */
public abstract class Criteria<T extends BaseEntity> {

	private Stream<T> found;
	
	public Criteria(Stream<T> found) {
		this.found=found;
	}
	
	protected Stream<T> getFound() {
		return found;
	}
	
	protected void setFound(Stream<T> found) {
		this.found = found;
	}
	
	public Criteria<T> addId(Long id) {
		found = found.filter(e -> e.getId().equals(id));
		return this;
	}
	
	public Criteria<T> addName(String name) {
		found = found.filter(e -> e.getName().equals(name));
		return this;
	}
	
	public Stream<T> find(){
		return found;
	}
	
}
