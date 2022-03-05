import java.util.Random;
/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author Ryan Rutishauser.
 * @version    2022.03.05
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    
    private int[] dayCounts;
    
    private int[] monthCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;
    
    private Random randomizer;
    
    private int numEntries;

    /**
     * Create an object to analyze hourly web accesses.
     * @parameter allows you to create a logFile with a with unique assigned name.
     */
    public LogAnalyzer(String logFile)
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        
        dayCounts = new int[29];
        
        monthCounts = new int[13];
        
        randomizer = new Random();
        
        numEntries = 10 + randomizer.nextInt(90);
        
        LogfileCreator writer = new LogfileCreator();
        writer.createFile(logFile, numEntries);
        // Create the reader to obtain the data.
        reader = new LogfileReader(logFile);   
        
        
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }
    
    /**
     * Analyze the daily access data from the log file.
     */
    public void analyzeDailyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int day = entry.getDay();
            dayCounts[day]++;
        }
    }
    
    /**
     * Analyze the monthly access data from the log file.
     */
    public void analyzeMonthlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int month = entry.getMonth();
            monthCounts[month]++;
        }
    }

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        int hour = 0;
        while(hour < hourCounts.length) {
            System.out.println(hour + ": " + hourCounts[hour]);
            ++hour;
        }
    }
    
    /**
     * Print the daily counts.
     * These should have been set with a prior
     * call to analyzeDailyData.
     */
    public void printDailyCounts()
    {
        System.out.println("Daily: Count");
        int day = 1;
        while(day < dayCounts.length) {
            System.out.println(day + ": " + dayCounts[day]);
            ++day;
        }
    }
    
    /**
     * Print the monthly counts.
     * These should have been set with a prior
     * call to analyzeMonthlyData.
     */
    public void totalAccessesPerMonth()
    {
        System.out.println("Month: Count");
        int month = 1;
        while(month < monthCounts.length) {
            System.out.println((month) + ": " + monthCounts[month]);
            ++month;
        }
    }
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
    
    /**
     * Keeps track of the total number of accesses for the year
     */
    public int numberOfAccesses()
    {
        //iterate over all lines in the file and add 1 to count for each row.
        
        int count = 0;
        for(int k=0; k<hourCounts.length; k++) {
            count += hourCounts[k];
        }
        return count;
        }

    /**
     * Returns the hour with the most amount of traffic. If two hours happen to tie for the greatest, the earliest hour will
     * printed.
     */
    public int busiestHour()
    {
        //iterate over all lines in the file and add 1 to count for each row.
        int hour = 0;
        int busiest = hourCounts[0];
        for(int k=0; k<hourCounts.length; k++) {
        if(hourCounts[k] > busiest){
            busiest = hourCounts[k];
            hour = k;
        }
        }
        return hour;
    }
        
    /**
     * Returns the hour with the least amount of traffic. If two hours happen to tie for the lowest, the earliest hour will
     * printed.
     */
    public int quietestHour()
    {
        //iterate over all lines in the file and add 1 to count for each row.
        int hour = 0;
        int quietest = hourCounts[0];
        for(int k=0; k<hourCounts.length; k++) {
        if(hourCounts[k] < quietest){
            quietest = hourCounts[k];
            hour = k;
        }
        }
        return hour;
    }
    
    /**
     * Returns the day with the least amount of traffic. If two days happen to tie for the lowest, the earliest day will
     * printed.
     */
    public int quietestDay()
    {
        //iterate over all lines in the file and add 1 to count for each row.
        int day = 1;
        int quietest = dayCounts[1];
        for(int k=1; k<dayCounts.length; k++) {
        if(dayCounts[k] < quietest){
            quietest = dayCounts[k];
            day = k;
        }
        }
        return day;
    }
    
    /**
     * Returns the day with the least amount of traffic. If two days happen to tie for the lowest, the earliest day will
     * printed.
     */
    public int busiestDay()
    {
        //iterate over all lines in the file and add 1 to count for each row.
        int day = 1;
        int busiest = dayCounts[1];
        for(int k=1; k<dayCounts.length; k++) {
        if(dayCounts[k] > busiest){
            busiest = dayCounts[k];
            day = k;
        }
        }
        return day;
    }
    
    /**
     * Returns the month with the least amount of traffic. If two days happen to tie for the lowest, the earliest month will
     * printed.
     */
    public int quietestMonth()
    {
        //iterate over all lines in the file and add 1 to count for each row.
        int month = 1;
        int quietest = monthCounts[1];
        for(int k=1; k<monthCounts.length; k++) {
        if(monthCounts[k] < quietest){
            quietest = monthCounts[k];
            month = k;
        }
        }
        return month;
    }
    
    /**
     * Returns the month with the least amount of traffic. If two months happen to tie for the lowest, the earliest month will
     * printed.
     */
    public int busiestMonth()
    {
        //iterate over all lines in the file and add 1 to count for each row.
        int month = 1;
        int busiest = monthCounts[1];
        for(int k=1; k<monthCounts.length; k++) {
        if(monthCounts[k] > busiest){
            busiest = monthCounts[k];
            month = k;
        }
        }
        return month;
    }
    
    /**
     * Returns the hour with the most amount of traffic. If two hours happen to tie for the greatest, the earliest hour will
     * printed.
     */
    public int busiestTwoHour()
    {
        //iterate over all lines in the file and add 1 to count for each row.
        int hour = 0;
        int busiest = hourCounts[0];
        for(int k=0; k<hourCounts.length; k++) {
        if((hourCounts[k] + hourCounts[k+1]) > busiest){
            busiest = (hourCounts[k] + hourCounts[k+1]);
            hour = k;
        }
        }
        return hour;
    }
    
    /**
     * Returns the hour with the most amount of traffic. If two hours happen to tie for the greatest, the earliest hour will
     * printed.
     */
    public double averageAccessesPerMonth()
    {
        //iterate over all lines in the file and add 1 to count for each row.
        int total = 0;
        double average = 0;
        for(int k=0; k<monthCounts.length; k++) {
            total += monthCounts[k];
            average=((double)total/monthCounts.length);
        }
        return average;
        }
    }

