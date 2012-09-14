package srm336;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class ServiceNames {
   public String[] makeList(String[] services) {
      int n = services.length;
      HashMap<String, Input> inputs = new HashMap<String, Input>();
      for (int i = 0; i < n; i++) {
         String[] info = services[i].split(" ");
         for (int j = 1; j < info.length; j++) {
            Input input = inputs.get(info[j]);
            if (input == null) {
               input = new Input(info[j]);
               inputs.put(info[j], input);
            }
            input.addService(info[0]);
         }
      }
      Input[] inputArray = new Input[inputs.size()];
      int index = 0;
      for (Input input : inputs.values())
         inputArray[index++] = input;
      Arrays.sort(inputArray);
      String[] res = new String[inputArray.length];
      for (int i = 0; i < res.length; i++)
         res[i] = inputArray[i].toString();
      return res;
   }
}

class Input implements Comparable<Object> {
   String name;
   HashSet<String> service = new HashSet<String>();

   public Input(String name) {
      super();
      this.name = name;
   }

   public void addService(String serviceName) {
      service.add(serviceName);
   }

   public int compareTo(Object arg0) {
      return name.compareTo(((Input) arg0).name);
   }

   public String toString() {
      String res = name + " ==> ";
      String[] services = new String[service.size()];
      int index = 0;
      for (String serviceName : service)
         services[index++] = serviceName;
      Arrays.sort(services);
      for (int i = 0; i < services.length; i++)
         res += services[i] + (i == services.length - 1 ? "" : ", ");
      return res;
   }
}