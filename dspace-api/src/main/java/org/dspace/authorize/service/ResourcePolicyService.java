/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 * http://www.dspace.org/license/
 */
package org.dspace.authorize.service;

import java.sql.SQLException;
import java.util.List;

import org.dspace.authorize.AuthorizeException;
import org.dspace.authorize.ResourcePolicy;
import org.dspace.content.DSpaceObject;
import org.dspace.core.Context;
import org.dspace.eperson.EPerson;
import org.dspace.eperson.Group;
import org.dspace.service.DSpaceCRUDService;

/**
 * Service interface class for the ResourcePolicy object.
 * The implementation of this class is responsible for all business logic calls for the ResourcePolicy object and is
 * autowired by spring
 *
 * @author kevinvandevelde at atmire.com
 */
public interface ResourcePolicyService extends DSpaceCRUDService<ResourcePolicy> {


    public List<ResourcePolicy> find(Context c, DSpaceObject o) throws SQLException;

    public List<ResourcePolicy> find(Context c, DSpaceObject o, String type) throws SQLException;

    public List<ResourcePolicy> find(Context c, DSpaceObject o, int actionId) throws SQLException;

    public List<ResourcePolicy> find(Context c, DSpaceObject dso, Group group, int action) throws SQLException;

    public List<ResourcePolicy> find(Context context, Group group) throws SQLException;

    public List<ResourcePolicy> find(Context c, EPerson e, List<Group> groups, int action, int type_id)
        throws SQLException;

    /**
     * Look for ResourcePolicies by DSpaceObject, Group, and action, ignoring IDs with a specific PolicyID.
     * This method can be used to detect duplicate ResourcePolicies.
     *
     * @param notPolicyID ResourcePolicies with this ID will be ignored while looking out for equal ResourcePolicies.
     * @return List of resource policies for the same DSpaceObject, group and action but other policyID.
     * @throws SQLException
     */
    public List<ResourcePolicy> findByTypeGroupActionExceptId(Context context, DSpaceObject dso, Group group,
                                                              int action, int notPolicyID)
        throws SQLException;

    public String getActionText(ResourcePolicy resourcePolicy);

    public boolean isDateValid(ResourcePolicy resourcePolicy);

    public ResourcePolicy clone(Context context, ResourcePolicy resourcePolicy) throws SQLException, AuthorizeException;

    public void removeAllPolicies(Context c, DSpaceObject o) throws SQLException, AuthorizeException;

    public void removePolicies(Context c, DSpaceObject o, int actionId) throws SQLException, AuthorizeException;

    public void removePolicies(Context c, DSpaceObject o, String type) throws SQLException, AuthorizeException;

    public void removeDsoGroupPolicies(Context context, DSpaceObject dso, Group group)
        throws SQLException, AuthorizeException;

    public void removeDsoEPersonPolicies(Context context, DSpaceObject dso, EPerson ePerson)
        throws SQLException, AuthorizeException;

    public void removeGroupPolicies(Context c, Group group) throws SQLException;

    public void removeDsoAndTypeNotEqualsToPolicies(Context c, DSpaceObject o, String type)
        throws SQLException, AuthorizeException;

    /**
     * Return a list of policies for an object that match the action except the record labeled with the rpType
     *
     * @param c        context
     * @param o        DSpaceObject policies relate to
     * @param actionID action (defined in class Constants)
     * @param rpType   the resource policy type
     * @return list of resource policies
     * @throws SQLException if there's a database problem
     */
    public List<ResourcePolicy> findExceptRpType(Context c, DSpaceObject o, int actionID, String rpType)
            throws SQLException;
}
