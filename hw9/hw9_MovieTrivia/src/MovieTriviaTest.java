import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import file.MovieDB;

class MovieTriviaTest {

	// instance of movie trivia object to test
	MovieTrivia mt;
	// instance of movieDB object
	MovieDB movieDB;

	@BeforeEach
	void setUp() throws Exception {
		// initialize movie trivia object
		mt = new MovieTrivia();

		// set up movie trivia object
		mt.setUp("moviedata.txt", "movieratings.csv");

		// get instance of movieDB object from movie trivia object
		movieDB = mt.movieDB;
	}

	@Test
	void testSetUp() {
		assertEquals(6, movieDB.getActorsInfo().size(),
				"actorsInfo should contain 6 actors after reading moviedata.txt.");
		assertEquals(7, movieDB.getMoviesInfo().size(),
				"moviesInfo should contain 7 movies after reading movieratings.csv.");

		assertEquals("meryl streep", movieDB.getActorsInfo().get(0).getName(),
				"\"meryl streep\" should be the name of the first actor in actorsInfo.");
		assertEquals(3, movieDB.getActorsInfo().get(0).getMoviesCast().size(),
				"The first actor listed in actorsInfo should have 3 movies in their moviesCasted list.");
		assertEquals("doubt", movieDB.getActorsInfo().get(0).getMoviesCast().get(0),
				"\"doubt\" should be the name of the first movie in the moviesCasted list of the first actor listed in actorsInfo.");

		assertEquals("doubt", movieDB.getMoviesInfo().get(0).getName(),
				"\"doubt\" should be the name of the first movie in moviesInfo.");
		assertEquals(79, movieDB.getMoviesInfo().get(0).getCriticRating(),
				"The critics rating for the first movie in moviesInfo is incorrect.");
		assertEquals(78, movieDB.getMoviesInfo().get(0).getAudienceRating(),
				"The audience rating for the first movie in moviesInfo is incorrect.");
	}

	@Test
	void testInsertActor() {

		// insert new actor with new movies
		mt.insertActor("test1", new String[] { "testmovie1", "testmovie2" }, movieDB.getActorsInfo());
		assertEquals(7, movieDB.getActorsInfo().size(),
				"After inserting an actor, the size of actorsInfo should have increased by 1.");
		assertEquals("test1", movieDB.getActorsInfo().get(movieDB.getActorsInfo().size() - 1).getName(),
				"After inserting actor \"test1\", the name of the last actor in actorsInfo should be \"test1\".");
		assertEquals(2, movieDB.getActorsInfo().get(movieDB.getActorsInfo().size() - 1).getMoviesCast().size(),
				"Actor \"test1\" should have 2 movies in their moviesCasted list.");
		assertEquals("testmovie1",
				movieDB.getActorsInfo().get(movieDB.getActorsInfo().size() - 1).getMoviesCast().get(0),
				"\"testmovie1\" should be the first movie in test1's moviesCasted list.");

		// insert existing actor with new movies
		mt.insertActor("   Meryl STReep      ", new String[] { "   DOUBT      ", "     Something New     " },
				movieDB.getActorsInfo());
		assertEquals(7, movieDB.getActorsInfo().size(),
				"Since \"meryl streep\" is already in actorsInfo, inserting \"   Meryl STReep      \" again should not increase the size of actorsInfo.");
		assertEquals(4, movieDB.getActorsInfo().get(0).getMoviesCast().size());
		
		// insert the actor with the movie which both exist
		mt.insertActor("meryl streep", new String [] {"Sophie's choice"}, movieDB.getActorsInfo());
		assertEquals(7, movieDB.getActorsInfo().size());
		assertEquals("meryl streep", movieDB.getActorsInfo().get(0).getName());
		assertEquals(4, movieDB.getActorsInfo().get(0).getMoviesCast().size());
		assertEquals("doubt", movieDB.getActorsInfo().get(0).getMoviesCast().get(0));
				
		// insert the actor with no movies
		mt.insertActor("Jackson", new String [] {}, movieDB.getActorsInfo());
		assertEquals(8, movieDB.getActorsInfo().size());
		assertEquals("jackson", movieDB.getActorsInfo().get(movieDB.getActorsInfo().size() - 1).getName());
		assertEquals(0, movieDB.getActorsInfo().get(movieDB.getActorsInfo().size() - 1).getMoviesCast().size());
		
				
		// look up and inspect movies for existing actor
		// note, this requires the use of properly implemented selectWhereActorIs method
		// you can comment out these two lines until you have a selectWhereActorIs
		// method
		assertEquals(4, mt.selectWhereActorIs("meryl streep", movieDB.getActorsInfo()).size(),
				"After inserting Meryl Streep again with 2 movies, only one of which is not on the list yet, the number of movies \"meryl streep\" appeared in should be 4.");
		assertTrue(mt.selectWhereActorIs("meryl streep", movieDB.getActorsInfo()).contains("something new"),
				"After inserting Meryl Streep again with a new Movie \"     Something New     \", \"somenthing new\" should appear as one of the movies she has appeared in.");

		

	}

	@Test
	void testInsertRating() {

		// insert new ratings for new movie
		mt.insertRating("testmovie", new int[] { 59, 60 }, movieDB.getMoviesInfo());
		assertEquals(8, movieDB.getMoviesInfo().size(),
				"After inserting ratings for a movie that is not in moviesInfo yet, the size of moviesInfo should increase by 1.");
		assertEquals("testmovie", movieDB.getMoviesInfo().get(movieDB.getMoviesInfo().size() - 1).getName(),
				"After inserting a rating for \"testmovie\", the name of the last movie in moviessInfo should be \"testmovie\".");
		assertEquals(59, movieDB.getMoviesInfo().get(movieDB.getMoviesInfo().size() - 1).getCriticRating(),
				"The critics rating for \"testmovie\" is incorrect.");
		assertEquals(60, movieDB.getMoviesInfo().get(movieDB.getMoviesInfo().size() - 1).getAudienceRating(),
				"The audience rating for \"testmovie\" is incorrect.");

		// insert new ratings for existing movie
		mt.insertRating(" doUbt   ", new int[] { 100, 100 }, movieDB.getMoviesInfo());
		assertEquals(8, movieDB.getMoviesInfo().size(),
				"Since \"doubt\" is already in moviesInfo, inserting ratings for it should not increase the size of moviesInfo.");
		assertEquals("doubt", movieDB.getMoviesInfo().get(0).getName());
		assertEquals(100, movieDB.getMoviesInfo().get(0).getCriticRating());
		assertEquals(100, movieDB.getMoviesInfo().get(0).getAudienceRating());
		
		// rating is less than 0
		mt.insertRating("no work", new int [] {-5, 88}, movieDB.getMoviesInfo());
		assertEquals(8, movieDB.getMoviesInfo().size());
		assertEquals("doubt", movieDB.getMoviesInfo().get(0).getName());
		assertEquals(59, movieDB.getMoviesInfo().get(movieDB.getMoviesInfo().size() - 1).getCriticRating());
		assertEquals(60, movieDB.getMoviesInfo().get(movieDB.getMoviesInfo().size() - 1).getAudienceRating());
		
		// rating is greater than 100
		mt.insertRating("no work", new int [] {101, 88}, movieDB.getMoviesInfo());
		assertEquals(8, movieDB.getMoviesInfo().size());
		assertEquals("doubt", movieDB.getMoviesInfo().get(0).getName());
		assertEquals(59, movieDB.getMoviesInfo().get(movieDB.getMoviesInfo().size() - 1).getCriticRating());
		assertEquals(60, movieDB.getMoviesInfo().get(movieDB.getMoviesInfo().size() - 1).getAudienceRating());
		
		// ratings == null
		mt.insertRating(" doUbt", new int [] { }, movieDB.getMoviesInfo());
		assertEquals(8, movieDB.getMoviesInfo().size());
		assertEquals("doubt", movieDB.getMoviesInfo().get(0).getName());
		assertEquals(59, movieDB.getMoviesInfo().get(movieDB.getMoviesInfo().size() - 1).getCriticRating());
		assertEquals(60, movieDB.getMoviesInfo().get(movieDB.getMoviesInfo().size() - 1).getAudienceRating());
		
		// ratings.length != 2
		mt.insertRating(" doUbt", new int [] {120, 1000}, movieDB.getMoviesInfo());
		assertEquals(8, movieDB.getMoviesInfo().size());
		assertEquals("doubt", movieDB.getMoviesInfo().get(0).getName());
		assertEquals(59, movieDB.getMoviesInfo().get(movieDB.getMoviesInfo().size() - 1).getCriticRating());
		assertEquals(60, movieDB.getMoviesInfo().get(movieDB.getMoviesInfo().size() - 1).getAudienceRating());
		
		// look up and inspect movies based on newly inserted ratings
		// note, this requires the use of properly implemented selectWhereRatingIs
		// method
		// you can comment out these two lines until you have a selectWhereRatingIs
		// method
		assertEquals(1, mt.selectWhereRatingIs('>', 99, true, movieDB.getMoviesInfo()).size(),
				"After inserting a critic rating of 100 for \"doubt\", there should be 1 movie in moviesInfo with a critic rating greater than 99.");
		assertTrue(mt.selectWhereRatingIs('>', 99, true, movieDB.getMoviesInfo()).contains("doubt"),
				"After inserting the rating for \"doubt\", \"doubt\" should appear as a movie with critic rating greater than 99.");

	}

	@Test
	void testSelectWhereActorIs() {
		// actor exists
		assertEquals(3, mt.selectWhereActorIs("meryl streep", movieDB.getActorsInfo()).size(),
				"The number of movies \"meryl streep\" has appeared in should be 3.");
		assertEquals("doubt", mt.selectWhereActorIs("meryl streep", movieDB.getActorsInfo()).get(0),
				"\"doubt\" should show up as first in the list of movies \"meryl streep\" has appeared in.");

		// TODO add additional test case scenarios
		
		// actor exists, but no movie
		assertEquals(0, mt.selectWhereActorIs("Brandon Krakowsky", movieDB.getActorsInfo()).size());
		
		// actor does not exist
		assertEquals(0, mt.selectWhereActorIs("Jackson", movieDB.getActorsInfo()).size());
		
	}

	@Test
	void testSelectWhereMovieIs() {
		assertEquals(2, mt.selectWhereMovieIs("doubt", movieDB.getActorsInfo()).size(),
				"There should be 2 actors in \"doubt\".");
		assertEquals(true, mt.selectWhereMovieIs("doubt", movieDB.getActorsInfo()).contains("meryl streep"),
				"\"meryl streep\" should be an actor who appeared in \"doubt\".");
		assertEquals(true, mt.selectWhereMovieIs("doubt", movieDB.getActorsInfo()).contains("amy adams"),
				"\"amy adams\" should be an actor who appeared in \"doubt\".");

		// TODO add additional test case scenarios
		
		// non-existent movie
		assertEquals(0, mt.selectWhereMovieIs("Talent", movieDB.getActorsInfo()).size());
		
		// Movie names do not be case sensitive
		assertEquals(2, mt.selectWhereMovieIs(" the post   ", movieDB.getActorsInfo()).size());
		assertEquals(true, mt.selectWhereMovieIs("  The Post", movieDB.getActorsInfo()).contains("tom hanks"));
		assertEquals(true, mt.selectWhereMovieIs("The Post   ", movieDB.getActorsInfo()).contains("meryl streep"));
		assertEquals(false, mt.selectWhereMovieIs("The Post   ", movieDB.getActorsInfo()).contains("  BraD Pitt"));
	}

	@Test
	void testSelectWhereRatingIs() {
		assertEquals(6, mt.selectWhereRatingIs('>', 0, true, movieDB.getMoviesInfo()).size(),
				"There should be 6 movies where critics rating is greater than 0.");
		assertEquals(0, mt.selectWhereRatingIs('=', 65, false, movieDB.getMoviesInfo()).size(),
				"There should be no movie where audience rating is equal to 65.");
		assertEquals(2, mt.selectWhereRatingIs('<', 30, true, movieDB.getMoviesInfo()).size(),
				"There should be 2 movies where critics rating is less than 30.");

		// TODO add additional test case scenarios
		
		// Non-existent comparison 
		assertEquals(0, mt.selectWhereRatingIs('?', 0, true, movieDB.getMoviesInfo()).size());

		// A targetRating that is out of range
		assertEquals(0, mt.selectWhereRatingIs('>', 590, true, movieDB.getMoviesInfo()).size());

		// Some correct comparison with the rating is from critics
		assertEquals(5, mt.selectWhereRatingIs('>', 60, true, movieDB.getMoviesInfo()).size());
		assertEquals(1, mt.selectWhereRatingIs('=', 91, true, movieDB.getMoviesInfo()).size());
		assertEquals(4, mt.selectWhereRatingIs('<', 90, true, movieDB.getMoviesInfo()).size());
		
		// Some correct comparison with the rating is from audience
		assertEquals(1, mt.selectWhereRatingIs('>', 90, false, movieDB.getMoviesInfo()).size());
		assertEquals(1, mt.selectWhereRatingIs('=', 90, false, movieDB.getMoviesInfo()).size());
		assertEquals(2, mt.selectWhereRatingIs('<', 60, false, movieDB.getMoviesInfo()).size());
	}

	@Test
	void testGetCoActors() {
		assertEquals(2, mt.getCoActors("meryl streep", movieDB.getActorsInfo()).size(),
				"\"meryl streep\" should have 2 co-actors.");
		assertTrue(mt.getCoActors("meryl streep", movieDB.getActorsInfo()).contains("tom hanks"),
				"\"tom hanks\" was a co-actor of \"meryl streep\".");
		assertTrue(mt.getCoActors("meryl streep", movieDB.getActorsInfo()).contains("amy adams"),
				"\"amy adams\" was a co-actor of \"meryl streep\".");

		// TODO add additional test case scenarios
		
		// non-existent actors
		assertEquals(0, mt.getCoActors("Jackson", movieDB.getActorsInfo()).size());

		// with leading or trailing whitespace
		assertEquals(2, mt.getCoActors(" meryl streep ", movieDB.getActorsInfo()).size());
		assertTrue(mt.getCoActors("meryl streep ", movieDB.getActorsInfo()).contains("tom hanks"));
		assertTrue(mt.getCoActors(" meryl streep", movieDB.getActorsInfo()).contains("amy adams"));

        // not be case sensitive
		assertEquals(2, mt.getCoActors(" Meryl StrEep ", movieDB.getActorsInfo()).size());
		assertTrue(mt.getCoActors("meryl sTreep ", movieDB.getActorsInfo()).contains("tom hanks"));
		assertTrue(mt.getCoActors("MERYL streep", movieDB.getActorsInfo()).contains("amy adams"));
	}

	@Test
	void testGetCommonMovie() {
		assertEquals(1, mt.getCommonMovie("meryl streep", "tom hanks", movieDB.getActorsInfo()).size(),
				"\"tom hanks\" and \"meryl streep\" should have 1 movie in common.");
		assertTrue(mt.getCommonMovie("meryl streep", "tom hanks", movieDB.getActorsInfo()).contains("the post"),
				"\"the post\" should be a common movie between \"tom hanks\" and \"meryl streep\".");

		// TODO add additional test case scenarios
		
		// non-existent actors
		assertEquals(0, mt.getCommonMovie("Jackson", "tom hanks", movieDB.getActorsInfo()).size());

		// with leading or trailing whitespace
		assertEquals(1, mt.getCommonMovie(" meryl streep ", "  tom hanks", movieDB.getActorsInfo()).size());
		assertTrue(mt.getCommonMovie("meryl streep", "tom hanks", movieDB.getActorsInfo()).contains("the post"));

		// not be case sensitive
		assertEquals(1, mt.getCommonMovie(" Meryl Streep ", "tom hanks", movieDB.getActorsInfo()).size());
		assertTrue(mt.getCommonMovie("meryl streep", "TOM hanks", movieDB.getActorsInfo()).contains("the post"));
	
		// actor1 and actor2 are the same
		assertEquals(3, mt.getCommonMovie(" Meryl Streep ", " meryl streep", movieDB.getActorsInfo()).size());
	
	}

	@Test
	void testGoodMovies() {
		assertEquals(3, mt.goodMovies(movieDB.getMoviesInfo()).size(),
				"There should be 3 movies that are considered good movies, movies with both critics and audience rating that are greater than or equal to 85.");
		assertTrue(mt.goodMovies(movieDB.getMoviesInfo()).contains("jaws"),
				"\"jaws\" should be considered a good movie, since it's critics and audience ratings are both greater than or equal to 85.");

		// TODO add additional test case scenarios

		// movie is not contained
		assertFalse(mt.goodMovies(movieDB.getMoviesInfo()).contains("Doubt"));

		// movie is contained
		assertTrue(mt.goodMovies(movieDB.getMoviesInfo()).contains("rocky ii"));
		assertTrue(mt.goodMovies(movieDB.getMoviesInfo()).contains("et"));
	}

	@Test
	void testGetCommonActors() {
		assertEquals(1, mt.getCommonActors("doubt", "the post", movieDB.getActorsInfo()).size(),
				"There should be one actor that appeared in both \"doubt\" and \"the post\".");
		assertTrue(mt.getCommonActors("doubt", "the post", movieDB.getActorsInfo()).contains("meryl streep"),
				"The actor that appeared in both \"doubt\" and \"the post\" should be \"meryl streep\".");

		// TODO add additional test case scenarios
		
		// non-existent movie
		assertEquals(0, mt.getCommonActors("Talent", "the post", movieDB.getActorsInfo()).size());

		// movie1 and movie2 be the same
		assertEquals(2, mt.getCommonActors("The Post", "the post", movieDB.getActorsInfo()).size());
		assertTrue(mt.getCommonActors("The Post", "the post", movieDB.getActorsInfo()).contains("tom hanks"));

		// with leading or trailing whitespace
		assertEquals(1, mt.getCommonActors(" doubt", "the post ", movieDB.getActorsInfo()).size());
		assertTrue(mt.getCommonActors("doubt ", " the post", movieDB.getActorsInfo()).contains("meryl streep"));
	}

	@Test
	void testGetMean() {

		// TODO add ALL test case scenarios!
		
		// correct calculation
		assertEquals(67.85, mt.getMean(movieDB.getMoviesInfo())[0], 0.01);
		assertEquals(65.71, mt.getMean(movieDB.getMoviesInfo())[1], 0.01);

		// insert new movie with [0,0]
		mt.insertRating("Talent", new int [] {0, 0}, movieDB.getMoviesInfo());
		assertEquals(59.37, mt.getMean(movieDB.getMoviesInfo())[0], 0.01);
		
		assertEquals(57.5, mt.getMean(movieDB.getMoviesInfo())[1], 0.01);

		// insert new movie with [60, 60]
		mt.insertRating("Marvel", new int [] {60, 60}, movieDB.getMoviesInfo());
		assertEquals(59.44, mt.getMean(movieDB.getMoviesInfo())[0], 0.01);
		assertEquals(57.78, mt.getMean(movieDB.getMoviesInfo())[1], 0.01);

		// insert new movie with [90, 90]
		mt.insertRating("Deer", new int [] {90, 90}, movieDB.getMoviesInfo());
		assertEquals(62.5, mt.getMean(movieDB.getMoviesInfo())[0], 0.01);
		assertEquals(61, mt.getMean(movieDB.getMoviesInfo())[1], 0.01);
	}
}
