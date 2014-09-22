package tomb.supportsim.controllers;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import tomb.supportsim.connection.HibernateUtil;
import tomb.supportsim.models.Analyst;
import tomb.supportsim.models.SupportTicket;
import tomb.supportsim.models.enums.TicketStateEnum;
import tomb.supportsim.models.enums.TicketTypeEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 05/08/2014 Time: 18:52
 */
public class TicketReporter
{
  public static final int UNASSIGNED = 0;

  public static List<SupportTicket> getAllUnassignedTickets()
  {
    final List<Criterion> restrictions = new ArrayList<Criterion>();
    restrictions.add( Restrictions.eq( "assigneeId", UNASSIGNED ) );
    return (List<SupportTicket>) HibernateUtil.getEntityList( SupportTicket.class, restrictions );
  }

  public static Integer getOpenTicketCount( final int id )
  {
    final List<Criterion> restrictions = new ArrayList<Criterion>();
    restrictions.add(
      Restrictions.and( Restrictions.eq( "assigneeId", id ), Restrictions.ne( "state", TicketStateEnum.CLOSED ) ) );
    return HibernateUtil.getEntityCount( SupportTicket.class, restrictions );
  }

  public static List<SupportTicket> getTicketsByState( final TicketStateEnum ticketStateEnum )
  {
    final List<Criterion> restrictions = new ArrayList<Criterion>();
    restrictions.add( Restrictions.eq( "state", ticketStateEnum ) );
    return HibernateUtil.getEntityList( SupportTicket.class, restrictions );
  }

  public static Integer getTicketCountByType(final TicketTypeEnum ticketTypeEnum)
  {
    final List<Criterion> restrictions = new ArrayList<Criterion>(  );
    restrictions.add( Restrictions.eq( "type", ticketTypeEnum ));
    return  HibernateUtil.getEntityCount( SupportTicket.class, restrictions );
  }

  public static Integer getTotalTicketCount()
  {
    return HibernateUtil.getEntityCount( SupportTicket.class );
  }

  public static List<SupportTicket> getAllTickets()
  {
    return (List<SupportTicket>) HibernateUtil.getEntityList( SupportTicket.class);
  }

  public static List<SupportTicket> getClosedTicketsByAnalyst( final Analyst analyst )
  {
    final List<Criterion> restrictions = new ArrayList<Criterion>();
    restrictions.add( Restrictions.and( Restrictions.eq( "assigneeId", analyst.getId() ),
                                        Restrictions.eq( "state", TicketStateEnum.CLOSED ) ) );

    return HibernateUtil.getEntityList( SupportTicket.class, restrictions );
  }

  public static Integer getClosedTicketCount()
  {
    final List<Criterion> restrictions = new ArrayList<Criterion>(  );
    restrictions.add( Restrictions.eq( "state", TicketStateEnum.CLOSED ));
    return  HibernateUtil.getEntityCount( SupportTicket.class, restrictions );
  }
}
