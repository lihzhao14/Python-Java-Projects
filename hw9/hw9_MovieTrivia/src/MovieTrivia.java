import java.util.ArrayList;

import file.MovieDB;
import movies.Actor;
import movies.Movie;

/**
 * Movie trivia class providing different methods for querying and updating a movie database.
 */
public class MovieTrivia {
	
	/**
	 * Create instance of movie database
	 */
	MovieDB movieDB = new MovieDB();
	
	
	public static void main(String[] args) {
		
		//create instance of movie trivia class
		MovieTrivia mt = new MovieTrivia();
		
		//setup movie trivia class
		mt.setUp("moviedata.txt", "movieratings.csv");
	}
	
	/**
	 * Sets up the Movie Trivia class
	 * @param movieData .txt file
	 * @param movieRatings .csv file
	 */
	public void setUp(String movieData, String movieRatings) {
		//load movie database files
		movieDB.setUp(movieData, movieRatings);
		
		//print all actors and movies
		this.printAllActors();
		this.printAllMovies();		
	}
	
	/**
	 * Prints a list of all actors and the movies they acted in.
	 */
	public void printAllActors () {
		System.out.println(movieDB.getActorsInfo());
	}
	
	/**
	 * Prints a list of all movies and their ratings.
	 */
	public void printAllMovies () {
		System.out.println(movieDB.getMoviesInfo());
	}
	
	
	// TODO add additional methods as specified in the instructions PDF
	
	// utility methods
	/**
	 * Inserts given actor and his/her movies into database
	 * @param actor: the actor name as a string
	 * @param movies: a String array of movie names that the actor has acted in
	 * @param actorsInfo: the ArrayList that is to be inserted into/updated
	 * This method does not return anything.
	 */
	public void insertActor (String actor, String [] movies, ArrayList <Actor> actorsInfo) {
		
		//make the actor name is without space and in lower case
		 String actor_formed = actor.trim().toLowerCase(); 
	
		 
		// if the actor has been in the list, then update their corresponding movies
		for (Actor a: actorsInfo) {
			if (a.getName().equals(actor_formed)) {
				// update the corresponding movies information of the actor
				for (int i = 0; i < movies.length; i++) {
					// gets the moviesCast ArrayList of the current Actor object and stores it in a variable called moviesCasted
					ArrayList<String> moviesCasted = a.getMoviesCast();
					// checks if the current movie in the loop (with trimmed and lowercase name) is already in the moviesCasted ArrayList
					// If the movie is already in the moviesCasted ArrayList, this line skips the current iteration of the loop and moves to the next movie
					if (moviesCasted.contains(movies[i].trim().toLowerCase())) {
						continue;
					} else {
						moviesCasted.add(movies[i].trim().toLowerCase());
					}
				}
				return;
			}
		}
		// if the actor doesn't exist in the list, then add the new actor with their corresponding movies
		// If the given actor was not found in the actorsInfo ArrayList, creates a new Actor object with the given actor's name (in lowercase and trimmed form)
		Actor newActor = new Actor (actor_formed);
		// add the new actor to the actorsInfo ArrayList
		actorsInfo.add(newActor); 
		// add each movie (with trimmed and lowercase name) to the moviesCast ArrayList of the new Actor object.
		for (int i = 0; i < movies.length; i++) {
			newActor.getMoviesCast().add(movies[i].trim().toLowerCase());	//add the new actor's movie 
		}
		return;
	}
	
	/**
	 * Inserts given ratings for given movie into database
	 * @param movie: the movie name as a string
	 * @param ratings: ratings is an int array with 2 elements, the critics rating at index 0 and the audience rating at index 1
	 * @param moviesInfo: the ArrayList that is to be inserted into/updated
	 */
	
	public void insertRating (String movie, int [] ratings, ArrayList <Movie> moviesInfo) {
		
	}
	
	/**
	 * Given an actor, returns the list of all movies
	 * @param actor: the name of an actor as a String
	 * @param actorsInfo: the ArrayList to get the data from
	 * @return the list of all movies
	 */
	
	public ArrayList <String> selectWhereActorIs (String actor, ArrayList <Actor> actorsInfo) {
		
	}
	
	
	/**
	 * Given a movie, returns the list of all actors in that movie
	 * @param movie: the name of a movie as a String
	 * @param actorsInfo: the ArrayList to get the data from
	 * @return the list of all actors in that movie
	 */
	public ArrayList <String> selectWhereMovieIs (String movie, ArrayList <Actor> actorsInfo) {
		
	}
	
	
	
	/**
	 * This useful method returns a list of movies that satisfy an inequality or equality, based on the 
	 * comparison argument and the targeted rating argument
	 * @param comparison, either ‘=’, ‘>’, or ‘< ‘ and is passed in as a char
	 * @param targetRating, an integer
	 * @param isCritic, a boolean that represents whether we are interested in the critics rating or the audience rating.
	 * 					true = critic ratings, false = audience ratings
	 * @param moviesInfo, the ArrayList that is to be inserted into/updated
	 * @return a list of movies that satisfy an inequality or equality
	 */
	public ArrayList <String> selectWhereRatingIs (char comparison, int targetRating, boolean isCritic,
			ArrayList <Movie> moviesInfo) {
		
	}
	
	// More Fun Methods
	/**
	 * 
	 * @param actor: the name of an actor as a String
	 * @param actorsInfo: the ArrayList to search through
	 * @return a list of all actors that the given actor has ever worked with in any movie except the actor herself/himself
	 */
	public ArrayList <String> getCoActors (String actor, ArrayList <Actor> actorsInfo) {
		
	}
	
	
	/**
	 * 
	 * @param actor1: actor names as Strings
	 * @param actor2: actor names as Strings
	 * @param actorsInfo: the ArrayList to search through
	 * @return a list of movie names where both actors were cast
	 */
	public ArrayList <String> getCommonMovie (String actor1, String actor2, ArrayList <Actor>
	actorsInfo) {
		
	}
	
	
	
	/**
	 * 
	 * @param moviesInfo
	 * @return a list of movie names that both critics and the audience have rated above 85 (>= 85)
	 */
	public ArrayList <String> goodMovies (ArrayList <Movie> moviesInfo) {
		
	}
	
	
	
	/**
	 * Given a pair of movies, this method returns a list of actors that acted in both
movies.
	 * @param movie1: the names of movies as Strings
	 * @param movie2: the names of movies as Strings
	 * @param actorsInfo: the actor ArrayList
	 * @return a list of actors that acted in both movies
	 */
	public ArrayList <String> getCommonActors (String movie1, String movie2, ArrayList <Actor>
	actorsInfo) {
		
	}
	
	
	/**
	 * Given the moviesInfo DB, this static method returns the mean value of the critics ratings and the audience ratings.
	 * @param moviesInfo
	 * @return the mean value of the critics ratings and the audience ratings
	 */
	public static double [] getMean (ArrayList <Movie> moviesInfo) {
		
	}
	
	
	
	
	
}
