package de.christianbernstein.universe.provider;

public interface IServiceProvider {

    default String getServiceName(){
        return this.getClass().getName();
    }

}
