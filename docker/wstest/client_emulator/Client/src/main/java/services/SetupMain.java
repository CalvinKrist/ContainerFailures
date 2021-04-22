package services;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SetupMain {

    private ApacheHttpClient apacheHttpClient;

    // echoDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
    private Date xmlCal;

    // echoStruct
    private Struct struct;

    // echoSynthetic
    private Synthetic synthetic;

    // echoArray
    private Item[] itemArray;

    // getOrder
    private int orderId;
    private int customerId;

    // order for echoOrder
    private Order order;

    private String fileName;

    private SetupMain(){}

    public static void main(String[] args) {
        String hostName = args[0]; //arg[0]
        String portN = args[1]; //arg[1]
        int numberTest = Integer.parseInt(args[2]); //arg[2]
        int time = Integer.parseInt(args[3]); //arg[3]
        
        // Time to delay for compute-heavy tests
        int computeSeconds = 0;
        if (args.length == 5) {
            computeSeconds = Integer.parseInt(args[4]);
        }

        SetupMain setupMain = new SetupMain();

        setupMain.fileName = "Data_Test_" + numberTest + ".csv";
        setupMain.apacheHttpClient = new ApacheHttpClient(hostName, portN, setupMain.fileName, computeSeconds);

        String[] methodNames = {"echoDate","echoStruct","echoSynthetic","echoArray","getOrder","echoOrder"};

        for (String s: methodNames) {
            setupMain.prepare(s);
        }

        setupMain.setupTests(time);
    }

    private void setupTests(long totalTime){
        long start = System.nanoTime();
        int countIterations = 0;
        double random;
        while (TimeUnit.NANOSECONDS.toMinutes(System.nanoTime() - start) < totalTime){
            random = Math.random() * 100 + 0;
            if(random < 10){
                apacheHttpClient.sendPostRequest("echoVoid","");
            }
            else if(random >= 10 && random < 20){
                apacheHttpClient.sendPostRequest("echoInteger","1024");
            }
            else if(random >= 20 && random < 30){
                apacheHttpClient.sendPostRequest("echoFloat","10.24");
            }
            else if(random >= 30 && random < 40){
                apacheHttpClient.sendPostRequest("echoString","Hello World!");
            }
            else if(random >= 40 && random < 50){
                apacheHttpClient.sendPostRequest("echoDate",convertObjectToString(xmlCal));
            }
            else if(random >= 50 && random < 60){
                apacheHttpClient.sendPostRequest("echoStruct",convertObjectToString(struct));
            }
            else if(random >= 60 && random < 70){
                apacheHttpClient.sendPostRequest("echoSynthetic",convertObjectToString(synthetic));
            }
            else if(random >= 70 && random < 80){
                apacheHttpClient.sendPostRequest("echoArray",convertObjectToString(itemArray));
            }
            else if(random >= 80 && random < 90){
                apacheHttpClient.sendGetRequest("getOrder/" + orderId + "/" + customerId);
            }
            else if(random >= 90 && random < 100){
                apacheHttpClient.sendPostRequest("echoOrder",convertObjectToString(order));
            }
            countIterations++;
        }

        long end = System.nanoTime();
        double throughput = countIterations/(end-start);
        //System.out.println("Throughput: " + throughput);

        apacheHttpClient.getCsv().writeResult(countIterations, start, end, throughput);
    }

    private void prepare(String methodName) {
        int size = 100;
        switch (methodName) {
            case "echoDate":
                try {
                    xmlCal = new SimpleDateFormat("yyyyMMdd").parse("20171120");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case "echoStruct":
                struct = new Struct();
                struct.setVarInt(5);
                struct.setVarFloat(2.5f);
                struct.setVarString("Hello There!");
                break;
            case "echoSynthetic":
                struct = new Struct();
                struct.setVarInt(5);
                struct.setVarFloat(2.5f);
                struct.setVarString("Hello There!");
                byte[] bytes = new byte[size];
                synthetic = new Synthetic();
                synthetic.setStr("Hello World");
                synthetic.setS(struct);
                synthetic.setBytes(bytes);
                break;
            case "echoArray":
                itemArray = new Item[size];

                for (int i = 0; i < size; i++) {
                    itemArray[i] = new Item();
                    itemArray[i].setId(Integer.toString(i));
                    itemArray[i].setDescription("Item Description");
                    itemArray[i].setPrice(100.00f);
                    itemArray[i].setInventory(i * 100);
                    Location loc = new Location();
                    loc.setId("CA95050");
                    loc.setDescription("Santa Clara, California");
                    loc.setAddress("1000, network circle");
                    itemArray[i].setLocation(loc);

                    Date cal = null;
                    try {
                        cal = new SimpleDateFormat("yyyyMMdd").parse("20171120");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    itemArray[i].setCreationdate(cal);
                }
                break;
            case "getOrder":
                orderId = 1;
                customerId = 1;
                break;
            case "echoOrder":
                int id = 1;

                // create the order object
                Address ship = new Address();
                ship.setFirstName("Ship FirstName " + id);
                ship.setLastName("Ship LastName " + id);
                ship.setAddress1("Ship StreetAddress " + id);
                ship.setAddress2("Street Address Line 2 " + id);
                ship.setCity("City " + id);
                ship.setState("State " + id);
                ship.setZip("12345");
                Address bill = new Address();
                bill.setFirstName("Bill FirstName " + id);
                bill.setLastName("Bill LastName " + id);
                bill.setAddress1("Bill StreetAddress " + id);
                bill.setAddress2("Street Address Line 2 " + id);
                bill.setCity("City " + id);
                bill.setState("State " + id);
                bill.setZip("12345");
                Customer customer = new Customer();
                customer.setCustomerId(customerId);
                customer.setContactFirstName("FirstName " + id);
                customer.setContactLastName("LastName " + id);
                customer.setContactPhone(Integer.toString(id));

                Date date = null;
                try {
                    date = new SimpleDateFormat("yyyyMMdd").parse("20171120");
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                customer.setLastActivityDate(date);
                customer.setCreditCardNumber("" + id);
                customer.setCreditCardExpirationDate("" + id);
                customer.setBillingAddress(bill);
                customer.setShippingAddress(ship);

                order = new Order();
                order.setOrderId(orderId);
                order.setOrderStatus(1);
                order.setOrderDate(date);
                order.setOrderTotalAmount((float) 50);
                order.setCustomer(customer);

                List<LineItem> lines = new ArrayList<>();

                LineItem lineItem;
                for (int i = 0; i < size; i++) {
                    lineItem = new LineItem();
                    lineItem.setOrderId(orderId);
                    lineItem.setItemId(i + 1);
                    lineItem.setProductId(i);
                    lineItem.setProductDescription("Test Product " + i);
                    lineItem.setOrderQuantity(1);
                    lineItem.setUnitPrice(1.00F);
                    lines.add(lineItem);
                }
                order.setLineItems(lines);
                break;
        }
    }

    private String convertObjectToString(Object o){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
