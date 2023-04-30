package com.upes.devopsproj.config;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;

import com.upes.devopsproj.entity.Product;
import com.upes.devopsproj.entity.ProductCategory;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {
	
	
	private EntityManager entityManager;
	
	@Autowired
	public MyDataRestConfig(EntityManager theEntityManager)
	{
		entityManager=theEntityManager;
	}
	
	
	
	public void configureRepositoryConfiguration(RepositoryRestConfiguration config)
	{
		  HttpMethod[] theUnsupportedActions= {HttpMethod.PUT,HttpMethod.POST, HttpMethod.DELETE};
		  
		  config.getExposureConfiguration()
		  .forDomainType(Product.class )
		  .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
		  .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions));
		  
//product category
		  config.getExposureConfiguration()
		  .forDomainType(ProductCategory.class )
		  .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
		  .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions));
		  
		  
		  
		  exposeIds(config);
		  
		  
		  


	}



	private void exposeIds(RepositoryRestConfiguration config) {
		// TODO Auto-generated method stub
		
		Set<EntityType<?>> entities =entityManager.getMetamodel().getEntities();
		
		List<Class> entityClasses =new ArrayList<>();
		
		for(EntityType tempEntityType : entities)
		{
			entityClasses.add(tempEntityType.getJavaType());
		}
		
		
		Class[] domainTypes = entityClasses.toArray(new Class[0]);
		config.exposeIdsFor(domainTypes);
		
		
		
		
	}
	
	

}
