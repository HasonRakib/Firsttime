import java.util.Scanner;

public class App {
    private static Scanner scanner = new Scanner(System.in);//static variable used on a specific data type
    
    public static void main(String[] args){
        System.out.println("Classpath: " + System.getProperty("java.class.path"));
      databasehelper.createTables();

        while (true) { //this loop keeps looping through all the options offered until the exit option is chosen
             System.out.println("1. Add a book");
             System.out.println("2. View all books");
             System.out.println("3. Update a book");
             System.out.println("4. Delete a book");
             System.out.println("5. Exit");
             System.out.print("Enter your choice: ");
             int choice = scanner.nextInt();
             scanner.nextLine(); // consume newline
        
                switch (choice) {//the switch provides the various options that can be used for the system
                     case 1:
                         addBook();
                         break;
                     case 2:
                        viewBooks();
                         break;
                     case 3:
                         updateBook();
                         break;
                     case 4:
                        deleteBook();
                        break;
                     case 5:
                        System.out.println("Exiting...");//this option makes sure that you can exit the loop
                        System.exit(0);
                        break;
                      default:
                        System.out.println("Invalid choice! Please enter a number between 1 and 5.");
                    }
                }
            }
        
            private static void addBook() {//function for adding the book and its author
                System.out.print("Enter book title: ");
                String title = scanner.nextLine();
                System.out.print("Enter book author: ");
                String author = scanner.nextLine();
        
                databasehelper.addBook(title, author);
            }
                
        
            private static void viewBooks() {//function for viewing all the saved books
                databasehelper.viewBooks();
            }


            private static void updateBook() {//function for updating the saved books based on their indexes of how they were saved
                System.out.print("Enter the id of the book to update: ");
                int id = scanner.nextInt();
                scanner.nextLine(); // consume newline
        
                System.out.print("Enter new title: ");
                String newTitle = scanner.nextLine();
                System.out.print("Enter new author: ");
                String newAuthor = scanner.nextLine();
        
                databasehelper.updateBook(id, newTitle, newAuthor);
            }
        
            private static void deleteBook() {//function for deleting any of the saved books 
                System.out.print("Enter the id of the book to delete: ");
                int id = scanner.nextInt();
                scanner.nextLine(); // consume newline

                databasehelper.deleteBook(id);
            }
}
            
        




