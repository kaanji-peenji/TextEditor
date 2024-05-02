package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println(">>");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            var filePath = "C:\\Users\\Veena\\OneDrive\\OneDrive\\Documents\\input.txt";
            var noOfLines = java.nio.file.Files.lines(Paths.get(filePath)).count();
            String input = scanner.nextLine();
            if (input.length() == 4 && input.equals("list") && noOfLines > 0) {
                try (BufferedReader inputFileReader = new BufferedReader(new FileReader(filePath))) {
                    String line = null;
                    var i = 1;
                    while ((line = inputFileReader.readLine()) != null) {
                        if (noOfLines >= i) {
                            System.out.println(i + " : " + line);
                            i++;
                        }
                    }
                }
            } else if (input.length() >= 5 && input.startsWith("del") && input.contains(" ") && noOfLines > 0) {
                String[] splitInput = input.split(" ");
                File tempFile = new File("tempFile.txt");
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
                if (splitInput.length == 2 && splitInput[0].equals("del") && Integer.valueOf(splitInput[1]) <= noOfLines) {
                    int lineNumberToBeDeleted = Integer.valueOf(splitInput[1]);
                    try (BufferedReader inputFileReader = new BufferedReader(new FileReader(filePath))) {
                        String line = null;
                        List<String> lines = new ArrayList<>();
                        while ((line = inputFileReader.readLine()) != null) {
                            lines.add(line);
                        }
                        for (int i = 0; i < lines.size(); i++) {
                            if (lineNumberToBeDeleted == (i + 1)) {
                                continue;
                            } else {
                                writer.write(lines.get(i));
                                writer.newLine();
                            }
                        }
                    }
                    writer.close();

                    new FileOutputStream(filePath).close();
                    try (BufferedReader inputFileReader = new BufferedReader(new FileReader(tempFile))) {
                        String line = null;
                        BufferedWriter writer1 = new BufferedWriter(new FileWriter(new File(filePath)));
                        while ((line = inputFileReader.readLine()) != null) {
                            writer1.write(line);
                            writer1.newLine();
                        }
                        writer1.close();
                    }
                    tempFile.delete();
                } else {
                    System.out.println("Please enter the correct line number!");
                }
            } else if (input.length() >= 5 && input.startsWith("ins") && input.contains(" ")) {
                String[] splitInput = input.split(" ");
                if (splitInput.length == 2 && splitInput[0].equals("ins") && Integer.valueOf(splitInput[1]) <= noOfLines) {
                    int lineNumberToBeInserted = Integer.parseInt(splitInput[1]);
                    System.out.println("Enter the line to be inserted");
                    String lineToBeInserted = scanner.nextLine();
                    try (BufferedReader inputFileReader = new BufferedReader(new FileReader(filePath))) {
                        List<String> lines = new ArrayList<>();
                        String line = null;
                        while ((line = inputFileReader.readLine()) != null) {
                            lines.add(line);
                        }
                        lines.add(lineNumberToBeInserted, lineToBeInserted);
                        new FileOutputStream(filePath).close();
                        BufferedWriter writer1 = new BufferedWriter(new FileWriter(new File(filePath)));
                        for (int i = 0; i < lines.size(); i++) {
                            writer1.write(lines.get(i));
                            writer1.newLine();
                        }
                        writer1.close();
                    }

                } else {
                    System.out.println("Please provide the correct line number!");
                }
            } else if (input.length() == 4 && input.equals("save")) {
                System.out.println("File Saved");
            } else if (input.length() == 4 && input.equals("quit")) {
                System.out.println("Quit!");
                break;
            } else {
                System.out.println("Please enter the correct input!");
            }
        }
        scanner.close();
    }
}