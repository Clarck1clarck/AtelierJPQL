/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package streaming.test;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import junit.framework.Assert;
import org.junit.Test;
import streaming.entity.Film;

/**
 *
 * @author Administrateur
 */
public class EntityManagerTest {
    
    @Test 
    public void req6(){
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        Query query = em.createQuery("SELECT COUNT (f) FROM Film f JOIN f.realisateurs r WHERE r.nom='Polanski'");
        long total = (long)query.getSingleResult();
        Assert.assertEquals(2L, total);
        
    }
    
//    @Test 
    public void req5(){
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        Query query = em.createQuery("SELECT COUNT(l) FROM Lien l JOIN l.film f WHERE F.titre='Big Lebowski (The)'");
        long total = (long )query.getSingleResult();
        Assert.assertEquals(1L, total);
        
    }
    
//    @Test
    public void req4(){
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        Query query = em.createQuery("SELECT MIN(f.annee) FROM Film f");
        int min = (int) query.getSingleResult();
        
        Assert.assertEquals(1968, min);
        
    }
    
//    @Test
    public void req3(){
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        Query query = em.createQuery("SELECT f FROM Film f");
//        long n = (long) query
    }
    
//    @Test
    public void req2(){
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        Query query = em.createQuery("SELECT f FROM Film f");
        List<Film> films = query.getResultList();
        int n = films.size();
        Assert.assertEquals(4, n);
   }
    
//    @Test
    public void req1(){
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        Film f = em.find(Film.class, 4L);
        
        Assert.assertEquals("Fargo", f.getTitre());
        
    }

//    @Test
    public void createQueryOK() {
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();

        Query query = em.createQuery("SELECT f FROM Film f");
        List<Film> films = query.getResultList();

        System.out.println(films);

    }

//    @Test
    public void removeOK() {
        
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();

        em.getTransaction().begin();

        Film f = em.find(Film.class, 151L);
        em.remove(f);

        em.getTransaction().commit();
    }

//    @Test
    public void mergeOK() {

        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        Film f = new Film();
        f.setId(251L);
        f.setTitre("*** MODIF ***");
        
        em.getTransaction().begin();
        
        em.merge(f);
        
        em.getTransaction().commit();
    }

//    @Test
    public void persistOK() {

        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        Film f = new Film();
        f.setTitre("Test");

        em.getTransaction().begin();
        
        em.persist(f);
        
        em.getTransaction().commit();
    }

//    @Test
    public void findOK() {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU");
        EntityManager em = factory.createEntityManager();

        Film f = em.find(Film.class, 2L);
        System.out.println(f);
    }

}
