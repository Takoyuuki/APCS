public class LogMessage
{
   private String machineId;
   private String description;

   /* Part (a) */
   public LogMessage(String message)
   {
      machineId = message.split(":", 1)[0];
      description = message.split(":", 1)[1];
      
   }

   /* Part (b) */
   public boolean containsWord(String keyword)
   {
      
      
   }

   public String getMachineId()
   { 
      return machineId; }

   public String getDescription()
   { 
      return description; }

   public String toString()
   {
      return getMachineId() + ":" + getDescription();
   }
}
