import java.util.List;
import java.util.Scanner;

public class MovieDatabaseApp {
    private static MovieDatabase movieDatabase = new MovieDatabase();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Movie Database Menu:");
            System.out.println("1. Add Movie");
            System.out.println("2. Delete Movie");
            System.out.println("3. Search Movie");
            System.out.println("4. List Movies");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter director: ");
                    String director = scanner.nextLine();
                    System.out.print("Enter year: ");
                    int year = scanner.nextInt();
                    System.out.print("Enter rating: ");
                    double rating = scanner.nextDouble();
                    movieDatabase.addMovie(title, director, year, rating);
                    System.out.println("Movie added successfully.");
                    break;

                case 2:
                    System.out.print("Enter title of movie to delete: ");
                    String titleToDelete = scanner.nextLine();
                    movieDatabase.deleteMovie(titleToDelete);
                    System.out.println("Movie deleted successfully.");
                    break;

                case 3:
                    System.out.print("Enter title of movie to search: ");
                    String titleToSearch = scanner.nextLine();
                    Movie movie = movieDatabase.searchMovie(titleToSearch);
                    if (movie != null) {
                        System.out.println("Movie found: " + movie.getTitle() + ", " + movie.getDirector() + ", " + movie.getYear() + ", " + movie.getRating());
                    } else {
                        System.out.println("Movie not found.");
                    }
                    break;

                case 4:
                    List<Movie> movies = movieDatabase.listMovies();
                    if (movies.isEmpty()) {
                        System.out.println("No movies in the database.");
                    } else {
                        System.out.println("Movies in the database:");
                        for (Movie m : movies) {
                            System.out.println(m.getTitle() + ", " + m.getDirector() + ", " + m.getYear() + ", " + m.getRating());
                        }
                    }
                    break;

                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
