package tomb.supportsim.schedule;

import tomb.supportsim.controllers.AssignmentController;
import tomb.supportsim.controllers.TicketReporter;
import tomb.supportsim.models.Analyst;
import tomb.supportsim.models.SupportTicket;
import tomb.supportsim.models.enums.AssignmentMethodEnum;

import java.util.List;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 09/08/2014 Time: 01:50
 */
class AssignmentPoller
{

  public void run()
  {
    final List<SupportTicket> unassignedTickets = TicketReporter.getAllUnassignedTickets();
    if ( unassignedTickets != null )
    {
      AssignmentController assignmentController = new AssignmentController( AssignmentMethodEnum.RANDOM );
      for ( SupportTicket ticket : unassignedTickets )
      {
        final Analyst analyst = assignmentController.getAnalyst( ticket.getType() );
        assignmentController.assignTicket( ticket, analyst );
      }
    }
  }
}
