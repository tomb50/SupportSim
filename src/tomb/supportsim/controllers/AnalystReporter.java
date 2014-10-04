package tomb.supportsim.controllers;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import tomb.supportsim.connection.HibernateUtil;
import tomb.supportsim.models.Analyst;
import tomb.supportsim.models.enums.RoleEnum;
import tomb.supportsim.models.enums.WorkingStateEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: tombeadman Date: 05/08/2014 Time: 18:52
 */
public class AnalystReporter
{

  public static List<Analyst> getAllAnalysts()
  {
    return (List<Analyst>) HibernateUtil.getEntityList( Analyst.class );
  }

  private static List<Analyst> getWorkingAnalysts()
  {
    final List<Criterion> restrictions = new ArrayList<Criterion>();
    restrictions.add( Restrictions.eq( "state", WorkingStateEnum.WORKING ) );
    return (List<Analyst>) HibernateUtil.getEntityList( Analyst.class, restrictions );
  }

  private static List<Analyst> getNotWorkingAnalysts()
  {
    final List<Criterion> restrictions = new ArrayList<Criterion>();
    restrictions.add( Restrictions.eq( "state", WorkingStateEnum.NOTWORKING ) );
    return (List<Analyst>) HibernateUtil.getEntityList( Analyst.class, restrictions );
  }

  public static List<Analyst> getSuitableAnalysts( final RoleEnum roleEnum )
  {
    final List<Criterion> restrictions = new ArrayList<Criterion>();
    restrictions.add( Restrictions.eq( "state", WorkingStateEnum.WORKING ) );
    restrictions.add( Restrictions.or( Restrictions.eq( "role", RoleEnum.ALL ), Restrictions.eq( "role", roleEnum ) ) );
    return (List<Analyst>) HibernateUtil.getEntityList( Analyst.class, restrictions );
  }

  public static List<List<Analyst>> getActiveLoL()
  {
    final List lol = new ArrayList();
    for ( RoleEnum roleEnum : RoleEnum.values() )
    {
      lol.add( getSuitableAnalysts( roleEnum ) );
    }
    return lol;
  }



  public Integer getTotalNumberOfAnalysts()
  {
    return HibernateUtil.getEntityCount( Analyst.class );
  }
}
