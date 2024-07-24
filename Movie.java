public class Movie {
    public static void main(String[] args) {
        // Create a new movie object
        Movie movie = new Movie("Inception", "Christopher Nolan", 2010, 8.8);

        // Test getters
        System.out.println("Title: " + movie.getTitle());       // Output: Inception
        System.out.println("Director: " + movie.getDirector()); // Output: Christopher Nolan
        System.out.println("Year: " + movie.getYear());         // Output: 2010
        System.out.println("Rating: " + movie.getRating());     // Output: 8.8

        // Test setters
        movie.setTitle("The Dark Knight");
        movie.setDirector("Christopher Nolan");
        movie.setYear(2008);
        movie.setRating(9.0);

        // Test getters after setting new values
        System.out.println("Title: " + movie.getTitle());       // Output: The Dark Knight
        System.out.println("Director: " + movie.getDirector()); // Output: Christopher Nolan
        System.out.println("Year: " + movie.getYear());         // Output: 2008
        System.out.println("Rating: " + movie.getRating());     // Output: 9.0
    }
}
