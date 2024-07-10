package com.diegoborbadev.expensemanager.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@MappedSuperclass
public abstract class IdentityGeneratorEntity<ID extends Serializable> extends BaseModel<ID> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ID id;
}