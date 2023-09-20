/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.iit.sat.itmd4515.a20478885.config;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.enterprise.context.ApplicationScoped;

/**
 *
 * @author sas
 */
@ApplicationScoped
@DataSourceDefinition(
        name = "java:app/jdbc/itmd4515DS",
        className = "com.mysql.cj.jdbc.MysqlDataSource",
        serverName = "106.53.155.219",
        portNumber = 3306,
        databaseName = "sakila",
        user = "root",
        password = "dywj928",
        properties = {
            "zeroDateTimeBehavior=CONVERT_TO_NULL",
            "useSSL=false"
        }
)
public class Itmd4515DatabaseConfig {
    
}
