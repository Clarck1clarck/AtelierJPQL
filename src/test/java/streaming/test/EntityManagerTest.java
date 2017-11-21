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
import streaming.entity.Episode;
import streaming.entity.Film;

/**
 *
 * @author Administrateur
 */
public class EntityManagerTest {

//    @Test
    public void req15() {
        //15. Le nombre d'épisodes de la saison 8 de la série Dexter
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        Query query = em.createQuery(""
                + "SELECT   COUNT (ep) "
                + "FROM     Episode ep "
                + "         JOIN ep.saison sa "
                + "         JOIN sa.serie s "
                + "WHERE    s.titre='Dexter' "
                + "AND      ");
        long total = (long) query.getSingleResult();
        Assert.assertEquals(96L, total);
    }

//    @Test
    public void req14() {
        //    14. Le nombre total d'épisodes de la série Dexter
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        Query query = em.createQuery(""
                + "SELECT   COUNT (ep) "
                + "FROM     Episode ep "
                + "         JOIN ep.saison sa "
                + "         JOIN sa.serie s "
                + "WHERE    s.titre='Dexter'");
        long total = (long) query.getSingleResult();
        Assert.assertEquals(96L, total);
    }

//    @Test
    public void req13() {
        //    13. Le nombre de saisons de la série Dexter
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        Query query = em.createQuery(""
                + "SELECT   COUNT (sa) "
                + "FROM     Saison sa "
                + "         JOIN sa.serie s "
                + "WHERE    s.titre='Dexter'");
//        SELECT COUNT(l) FROM Lien l JOIN l.film f WHERE F.titre='Big Lebowski (The)'
        long total = (long) query.getSingleResult();
        Assert.assertEquals(8L, total);
    }

//    @Test
    public void req12() {
//        12. Le nombre de films policiers réalisés à la fois par les 2 frères Coen, et interprétés par Steve Buscemi
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        Query query = em.createQuery(""
                + "SELECT   COUNT (f) "
                + "FROM     Film f "
                + "         JOIN f.realisateurs r1 "
                + "         JOIN f.realisateurs r2 "
                + "         JOIN f.acteurs a "
                + "         JOIN f.genre g "
                + "WHERE    r1.nom='Coen' "
                + "AND      r2.nom='Coen' "
                + "AND      r1.prenom='Joel' "
                + "AND      r2.prenom='Ethan' "
                + "AND      a.nom='Buscemi' "
                + "AND      a.prenom='Steve' "
                + "AND      g.nom='Policier'");

        long total = (long) query.getSingleResult();
        Assert.assertEquals(1L, total);
    }

//    @Test
    public void req11() {
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        Query query = em.createQuery(""
                + "SELECT   COUNT (f) "
                + "FROM     Film f "
                + "         JOIN f.realisateurs r1 "
                + "         JOIN f.realisateurs r2 "
                + "         JOIN f.acteurs a "
                + "WHERE    r1.nom='Coen' "
                + "AND      r2.nom='Coen' "
                + "AND      r1.prenom='Joel' "
                + "AND      r2.prenom='Ethan' "
                + "AND      a.nom='Buscemi' "
                + "AND      a.prenom='Steve'");

        long total = (long) query.getSingleResult();
        Assert.assertEquals(0L, total);
    }

//    @Test
    public void req10() {
//        10. Le nombre de films réalisés à la fois par les 2 frères coen
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        Query query = em.createQuery(""
                + "SELECT   COUNT (f) "
                + "FROM     Film f "
                + "         JOIN f.realisateurs r1 "
                + "         JOIN f.realisateurs r2 "
                + "WHERE    r1.nom='Coen' "
                + "AND      r1.prenom='Joel' "
                + "AND      r2.nom='Coen' "
                + "AND      r2.prenom='Ethan'");
        
            //faire un INTERSECT avec un (2L, film.size); comme ex

        long total = (long) query.getSingleResult();
        Assert.assertEquals(2L, total);
    }
    
//    @Test
//    public void req10() {
////        10. Le nombre de films réalisés à la fois par les 2 frères coen
//        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
//        Query query = em.createQuery(""
//                + "SELECT   COUNT (f) "
//                + "FROM     Film f "
//                + "         JOIN f.realisateurs r1 "
//                + "         JOIN f.realisateurs r2 "
//                + "WHERE    r1.nom='Coen' "
//                + "AND      r1.prenom='Joel' "
//                + "AND      r2.nom='Coen' "
//                + "AND      r2.prenom='Ethan'");
//        
//            //faire un INTERSECT avec un (2L, film.size); comme ex
//
//        long total = (long) query.getSingleResult();
//        Assert.assertEquals(2L, total);
//    }

//    @Test
    public void req9() {
//        9. Le nomnbre de films réalisés par joel coen
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        Query query = em.createQuery(""
                + "SELECT   COUNT (f) "
                + "FROM     Film f "
                + "         JOIN f.realisateurs r "
                + "WHERE    r.nom='Coen' "
                + "AND      r.prenom='Joel'");

        long total = (long) query.getSingleResult();
        Assert.assertEquals(2L, total);
    }

//    @Test
    public void req8autreEx() {

        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        Query query = em.createQuery(""
                + "SELECT   f "
                + "FROM     Film f "
                + "         JOIN f.realisateurs r "
                + "         JOIN f.genre g "
                + "         JOIN f.pays p "
                + "WHERE    g.nom='Horreur' "
                + "         AND p.nom='UK' "
                + "         AND r.nom='Polanski' "
                + "         AND r.prenom='Roman'");

        Film film = (Film) query.getSingleResult();

        String titre = film.getTitre();

        Assert.assertEquals("Le bal des vampires", titre);
    }

//    @Test
    public void req8() {

        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        Query query = em.createQuery("SELECT (f) FROM Film f JOIN f.realisateurs r JOIN f.genre g JOIN f.pays p WHERE g.nom='Horreur' AND p.nom='UK' AND r.nom='Polanski' AND r.prenom='Roman'");

        Film film = (Film) query.getSingleResult();

        String titre = film.getTitre();

        Assert.assertEquals("Le bal des vampires", titre);
    }

//    @Test
    public void req7() {

        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        Query query = em.createQuery("SELECT COUNT (f) FROM Film f JOIN f.acteurs a JOIN f.realisateurs r WHERE r.nom='Polanski' AND r.prenom='Roman'");

        long total = (long) query.getSingleResult();
        Assert.assertEquals(1L, total);
    }

//    @Test
    public void req6() {

//        EntityManagerFactory factory =Persistence.createEntityManagerFactory("Pu");
//        EntityManager em = factory.createEntityManager();
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        Query query = em.createQuery("SELECT COUNT (f) FROM Film f JOIN f.acteurs a WHERE a.nom='Polanski' AND a.prenom='Roman'");
        long total = (long) query.getSingleResult();

        Assert.assertEquals(1L, total);
    }

//    @Test
    public void req5() {
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        Query query = em.createQuery("SELECT COUNT (f) FROM Film f JOIN f.realisateurs r WHERE r.nom='Polanski'");
        long total = (long) query.getSingleResult();
        Assert.assertEquals(2L, total);

    }

//    @Test 
    public void req4() {
//        4. Nombre de liens du film 'Big Lebowski (The)'
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        Query query = em.createQuery("SELECT COUNT(l) FROM Lien l JOIN l.film f WHERE F.titre='Big Lebowski (The)'");
        long total = (long) query.getSingleResult();
        Assert.assertEquals(1L, total);

    }

//    @Test
    public void req3() {
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        Query query = em.createQuery("SELECT MIN(f.annee) FROM Film f");
        int min = (int) query.getSingleResult();

        Assert.assertEquals(1968, min);

    }

//    @Test
    public void req2auttreEx() {
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        Query query = em.createQuery("SELECT f FROM Film f");
//        long n = (long) query
    }

//    @Test
    public void req2() {
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        Query query = em.createQuery("SELECT f FROM Film f");
        List<Film> films = query.getResultList();
        int n = films.size();
        Assert.assertEquals(4, n);
    }

//    @Test
    public void req1() {
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
