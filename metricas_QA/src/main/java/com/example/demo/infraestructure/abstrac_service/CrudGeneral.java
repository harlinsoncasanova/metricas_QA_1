package com.example.demo.infraestructure.abstrac_service;

public interface CrudGeneral <RQ,RS,ID>{
    public RS create(RQ request);

    public RS get(ID id);

    public RS update(RQ request, ID id);

    public void delete(ID id);
}