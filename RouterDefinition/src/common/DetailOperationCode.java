package common;

/**
 * Created by Jeong Taegyun on 2017-04-25.
 */
public enum DetailOperationCode {
    /* etc */
    TURN_ON_POWER,
    TURN_OFF_POWER,

    /* Get router information operations */
    GET_CONNECTED_DEVICES,
    GET_SECURITY_CONFIGURATION,
    GET_PORTFORWARDING_CONFIGURATION,
    GET_IPALLOCATE_CONFIGURATION,
    GET_DHCP_CONFIGURATION,

    /* Set router information operations */
    SET_SECURITY_CONFIGURATION,
    SET_PORTFORWARDING_CONFIGURATION,
    SET_IPALLOCATE_CONFIGURATION,
    SET_DHCP_CONFIGURATION,

    /* Error operations */
    UNKNOWN_OPERATION
}
