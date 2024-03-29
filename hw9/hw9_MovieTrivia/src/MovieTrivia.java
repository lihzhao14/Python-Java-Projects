import java.util.ArrayList;

import file.MovieDB;
import movies.Actor;
import movies.Movie;

/**
 * Movie trivia class providing different methods for querying and updating a movie database.
 * @author Haojie Zheng & Lihong Zhao 
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
		
		// make the actor name is without space and in lower case
	    String actor_formed = actor.trim().toLowerCase();
	
	    
	    Actor targetActor = null;
	    
	    // check if the actor is in the actorsInfo ArrayList 
	    for (Actor a : actorsInfo) {
	        if (a.getName().equals(actor_formed)) {
	            targetActor = a;
	            break;
	        }
	    }
	    
	    // if not found, create a new actor
	    if (targetActor == null) {
	        targetActor = new Actor(actor_formed);
	        actorsInfo.add(targetActor);
	    }
	
	    // update the target actor's corresponding movies
	    for (String movie : movies) {
	    	// make the movie name is without space and in lower case
	        String movie_formed = movie.trim().toLowerCase();
	        // gets the moviesCast ArrayList of the current Actor object and stores it in a variable called moviesCasted
	        ArrayList<String> moviesCasted = targetActor.getMoviesCast();
	        // If the movie is not present in the list, it adds the movie to the list which helps prevent duplicate movies in the list
	        if (!moviesCasted.contains(movie_formed)) {
	            moviesCasted.add(movie_formed);
	        }
	    }
	}
	
	/**
	 * Inserts given ratings for given movie into database
	 * @param movie: the movie name as a string
	 * @param ratings: ratings is an int array with 2 elements, the critics rating at index 0 and the audience rating at index 1
	 * @param moviesInfo: the ArrayList that is to be inserted into/updated
	 */
	
	public void insertRating (String movie, int [] ratings, ArrayList <Movie> moviesInfo) {
	    // Validate the movie and ratings are not empty and the length of ratings is equal to 2
	    if (movie == null || ratings == null || ratings.length != 2) {
	        return;
	    }
	    
	    // Trim and convert the movie name to lower case before checking ratings
	    String movie_formed = movie.trim().toLowerCase();
	    
	    // Make sure the rating is a positive integer within two digits
	    for (int rating : ratings) {
	        if (rating < 0 || rating > 100) {
	            return;
	        }
	    }

	    // Use a flag to indicate whether a movie was found and updated
	    boolean movieFound = false;

	    for (Movie movieInfo : moviesInfo) {
	        if (movieInfo.getName().equals(movie_formed)) {
	            movieInfo.setCriticRating(ratings[0]);	// insert the critics rating
	            movieInfo.setAudienceRating(ratings[1]);	// insert the audience rating
	            movieFound = true;
	            break; // Exit the loop once the movie has been found and updated.
	        }
	    }

	    // If the movie was not found in the moviesInfo ArrayList, create a new Movie object and add it.
	    if (!movieFound) {
	        Movie newMovie = new Movie(movie_formed, ratings[0], ratings[1]);
	        moviesInfo.add(newMovie);
	    }
	}
	
	/**
	 * Given an actor, returns the list of all movies
	 * @param actor: the name of an actor as a String
	 * @param actorsInfo: the ArrayList to get the data from
	 * @return a. Given an actor, returns the list of all movies
	 * 		   b. Given a non-existent actor, this method should return an empty list
	 */
	
	public ArrayList <String> selectWhereActorIs (String actor, ArrayList <Actor> actorsInfo) {
	    // Validate actor and actorsInfo are not empty
	    if (actor == null || actorsInfo == null) {
	        return new ArrayList<String>();
	    }
	    
	    // Trim and convert the actor name to lower case before checking
	    String actorName = actor.trim().toLowerCase();
	    
	    // Check if the given actor is in actorsInfo ArrayList
	    for (Actor actor_current : actorsInfo) {
	        if (actor_current.getName().equals(actorName)) {
	            return actor_current.getMoviesCast();
	        }
	    }

	    return new ArrayList<String>();
	}
	
	
	/**
	 * Given a movie, returns the list of all actors in that movie
	 * @param movie: the name of a movie as a String
	 * @param actorsInfo: the ArrayList to get the data from
	 * @return the list of all actors in that movie
	 */
	public ArrayList <String> selectWhereMovieIs (String movie, ArrayList <Actor> actorsInfo) {
		// Validate movie and actorsInfo are not empty
	    if (movie == null || actorsInfo == null) {
	        return new ArrayList<String>();
	    }
	    
    	// Trim and convert the movie name to lower case before checking
	    String movieName = movie.trim().toLowerCase();
	    
	    // Create a variable named actorsInMovie which is used to store the names of all actors who appeared in the given movie
	    ArrayList<String> actorsInMovie = new ArrayList<>();
	    for (Actor actorInfo : actorsInfo) {
	        // Iterate over the movies in the actor's cast list.
	        for (String movieInCast : actorInfo.getMoviesCast()) {
	            if (movieInCast.equals(movieName)) {
	                actorsInMovie.add(actorInfo.getName());
	                break; // Exit the inner loop once the movie is found.
	            }
	        }
	    }
	    return actorsInMovie;
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
		
	    ArrayList<String> movieName = new ArrayList<>();

	    // Validate targetRating and check if moviesInfo is null
	    if (targetRating < 0 || targetRating > 100 || moviesInfo == null) return new ArrayList<String>();
	    // Validate comparison values
	    if (comparison != '>' && comparison != '=' && comparison != '<') return new ArrayList<String>();

	    for (Movie movie : moviesInfo) {
	    	// determine whether to use the movie's critic rating or audience rating based on the isCritic boolean value
	    	// If isCritic is true, it assigns the critic rating to the rating variable;
	    	// otherwise, it assigns the audience rating
	        int rating = isCritic ? movie.getCriticRating() : movie.getAudienceRating();

//	        if ((comparison == '>' && rating > targetRating) || (comparison == '=' && rating == targetRating) || (comparison == '<' && rating < targetRating)) {
//	            res.add(movie.getName());
//	        }
	        
	        // Initialize a boolean variable to store the result of the comparison for each movie
	        boolean condition = false;
	        
	        // Three situations of comparison 
	        switch (comparison) {
	            case '>':
	                condition = rating > targetRating;
	                break;
	            case '=':
	                condition = rating == targetRating;
	                break;
	            case '<':
	                condition = rating < targetRating;
	                break;
	        }
	        
	        // If the condition is true, add the movie's name to the movieName list
	        if (condition) {
	        	movieName.add(movie.getName());
	        }
	    }
	    // Return the movieName list containing the names of movies that meet the critics/audience ratings
	    return movieName;
	}
	
	
	
	// More Fun Methods
	/**
	 * Obtain a list of all actors that the given actor has ever worked with in any movie except the actor herself/himself.
	 * @param actor: the name of an actor as a String
	 * @param actorsInfo: the ArrayList to search through
	 * @return a list of all actors that the given actor has ever worked with in any movie except the actor herself/himself
	 */
	public ArrayList <String> getCoActors (String actor, ArrayList <Actor> actorsInfo) {
		
		// Initialize an empty list to store the names of co-actors
	    ArrayList<String> actorCo = new ArrayList<>();
	    
	    // Validate that actor and actorsInfo are not null
	    if (actor == null || actorsInfo == null) {
	        return new ArrayList<String>();
	    }
	    
	    // Trim and convert the actor name to lower case before checking
	    String actorName = actor.trim().toLowerCase();
	    
	    // Get a list of all movies the given actor has acted in
	    ArrayList<String> moviesIn = selectWhereActorIs(actorName, actorsInfo);
	    
	    // Iterate through each movie the given actor has acted in
	    for (String movieIn : moviesIn) {
	        // Get a list of all actors in the current movie
	        ArrayList<String> actorsIn = selectWhereMovieIs(movieIn, actorsInfo);
	        
	        // Iterate through each actor in the current movie
	        for (String actorIn : actorsIn) {
	            // If the current actor is not the given actor and is not already in the res list,
	            // add the current actor to the res list
	            if (!actorIn.equals(actorName) && !actorCo.contains(actorIn)) {
	            	actorCo.add(actorIn);
	            }
	        }
	    }

	    // Return the res list containing the names of all co-actors the given actor has worked with
	    return actorCo;
	}
	
	
	/**
	 * Obtain a list of movie names where both actors were cast.
	 * @param actor1: actor names as Strings
	 * @param actor2: actor names as Strings
	 * @param actorsInfo: the ArrayList to search through
	 * @return a list of movie names where both actors were cast
	 */
	public ArrayList <String> getCommonMovie (String actor1, String actor2, ArrayList <Actor>
	actorsInfo) {
		
	    // Initialize an empty list to store the names of common movies
	    ArrayList<String> commonMovies = new ArrayList<>();

	    // Validate that actor1, actor2, and actorsInfo are not null
	    if (actor1 == null || actor2 == null || actorsInfo == null) {
	        return commonMovies;
	    }

	    // Get a list of all movies the first actor has acted in
	    ArrayList<String> movies1 = selectWhereActorIs(actor1.trim().toLowerCase(), actorsInfo);

	    // Get a list of all movies the second actor has acted in
	    ArrayList<String> movies2 = selectWhereActorIs(actor2.trim().toLowerCase(), actorsInfo);

	    // If either actor has not acted in any movies, return an empty list
	    if (movies1.isEmpty() || movies2.isEmpty()) return commonMovies;

	    // If both actor names are the same, return the list of movies for one of the actors
	    if (actor1.trim().toLowerCase().equals(actor2.trim().toLowerCase())) return movies1;

	    // Iterate through each movie the first actor has acted in
	    for (String movie : movies1) {
	        // If the second actor's list of movies contains the current movie, add the movie to the commonMovies list
	        if (movies2.contains(movie)) commonMovies.add(movie);
	    }

	    // Return the commonMovies list containing the names of common movies where both actors were cast
	    return commonMovies;
	}
	
	
	
	/**
	 * Obtain a list of movie names that both critics and the audience have rated above 85 (>= 85).
	 * @param moviesInfo
	 * @return a list of movie names that both critics and the audience have rated above 85 (>= 85)
	 */
	public ArrayList <String> goodMovies (ArrayList <Movie> moviesInfo) {
		// Create a new ArrayList to store the result
	    ArrayList<String> movieGreater85 = new ArrayList<>();

	    // Check if moviesInfo is null or empty
	    if (moviesInfo == null || moviesInfo.isEmpty()) {
	        return new ArrayList<>();
	    }

	    // Iterate through each movie in moviesInfo
	    for (Movie movie : moviesInfo) {
	        // Check if both criticRating and audienceRating are greater than or equal to 85
	        if (movie.getCriticRating() >= 85 && movie.getAudienceRating() >= 85) {
	            // Add the movie name to the result ArrayList
	        	movieGreater85.add(movie.getName());
	        }
	    }

	    // Return the result ArrayList
	    return movieGreater85;

	}
	
	
	
	/**
	 * Given a pair of movies, obtain a list of actors that acted in both movies.
	 * @param movie1: the names of movies as Strings
	 * @param movie2: the names of movies as Strings
	 * @param actorsInfo: the actor ArrayList
	 * @return a list of actors that acted in both movies
	 */
	public ArrayList <String> getCommonActors (String movie1, String movie2, ArrayList <Actor>
	actorsInfo) {
		 // Create a new ArrayList to store the result
	    ArrayList<String> actorBoth = new ArrayList<>();

	    // Check if movie1, movie2 or actorsInfo is null or empty
	    if (movie1 == null || movie2 == null || actorsInfo == null || actorsInfo.isEmpty()) return new ArrayList<>();
	    
	    // Get the list of actors in each movie
	    ArrayList<String> actors1 = selectWhereMovieIs(movie1, actorsInfo);
	    ArrayList<String> actors2 = selectWhereMovieIs(movie2, actorsInfo);

	    // Check if either of the movies has no actors
	    if (actors1.isEmpty() || actors2.isEmpty()) return new ArrayList<>();

	    // If both movies are the same, return the list of actors in movie1
	    if (movie1.trim().toLowerCase().equals(movie2.trim().toLowerCase())) return actors1;

	    // Iterate through the actors in movie1
	    for (String actor : actors1) {
	        // If an actor from movie1 is also in movie2, add the actor to the result list
	        if (actors2.contains(actor)) {
	        	actorBoth.add(actor);
	        }
	    }

	    // Return the result ArrayList
	    return actorBoth;
	}
	
	
	/**
	 * Given the moviesInfo DB, obtain the mean value of the critics ratings and the audience ratings.
	 * @param moviesInfo
	 * @return the mean value of the critics ratings and the audience ratings
	 */
	public static double [] getMean (ArrayList <Movie> moviesInfo) {
		
		// Initialize a double array to store the mean values for critics' ratings and audience ratings
	    double[] mean = new double[2];

	    // Validate the moviesInfo is null or empty
	    if (moviesInfo == null || moviesInfo.isEmpty()) {
	        return mean;
	    }

	    // Initialize variables to store the sum of the critics' ratings and audience ratings
	    double sumCritics = 0;
	    double sumAudience = 0;

	    // Iterate through the moviesInfo ArrayList
	    for (Movie movieInfo : moviesInfo) {
	        // Add the critic rating and audience rating of each movie to the respective sums
	        sumCritics += movieInfo.getCriticRating();
	        sumAudience += movieInfo.getAudienceRating();
	    }

	    // Calculate the mean of the critics' ratings and audience ratings
	    int size = moviesInfo.size();
	    mean[0] = sumCritics / size;
	    mean[1] = sumAudience / size;

	    // Return the mean values
	    return mean;
	}
	
	
	
	
	
}
