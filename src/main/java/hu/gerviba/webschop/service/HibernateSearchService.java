package hu.gerviba.webschop.service;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.apache.lucene.search.Query;
import org.hibernate.search.engine.ProjectionConstants;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.gerviba.webschop.dao.ItemEntityDao;
import hu.gerviba.webschop.model.ItemEntity;

@Service
public class HibernateSearchService {

	@Autowired
	EntityManager entityManager;
	
	@Transactional
	public List<ItemEntityDao> fuzzySearchItem(String matchingString) {
		FullTextEntityManager fullTextEntityManager =
				Search.getFullTextEntityManager(entityManager);
		 
		QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory() 
				.buildQueryBuilder()
				.forEntity(ItemEntity.class)
				.get();
		
		Query query = queryBuilder
				.keyword()
				.fuzzy()
				.withEditDistanceUpTo(2)
				.withPrefixLength(0)
				.onField("name").boostedTo(3F)
				.andField("abcdef").boostedTo(0.1F) //TODO: RENAME abcdef
				.matching(matchingString)
				.createQuery();
		
		@SuppressWarnings("unchecked")
		List<Object[]> results = (List<Object[]>) fullTextEntityManager
				.createFullTextQuery(query, ItemEntity.class)
				.setProjection(ProjectionConstants.THIS, ProjectionConstants.SCORE)
				.limitExecutionTimeTo(1, TimeUnit.SECONDS)
				.getResultList();
		
		return results.stream()
        		.filter(x -> x != null && x.length > 1 && x[0] != null && ((float) x[1]) >= 0.5)
//        		.peek(x -> System.out.println(((ItemEntity) x[0]).getName() + "\t" + x[1])) //TODO: Remove this debug line
        		.map(x -> x[0])
        		.distinct()
        		.map(x -> new ItemEntityDao((ItemEntity) x))
        		.collect(Collectors.toList());
	}

}