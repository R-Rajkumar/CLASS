
package com.arima.classanalyzer.webs.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.arima.classengine.webs package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetNearestGlobalProfiles_QNAME = new QName("http://webs.classengine.arima.com/", "getNearestGlobalProfiles");
    private final static QName _InsertExamPerformanceResponse_QNAME = new QName("http://webs.classengine.arima.com/", "insertExamPerformanceResponse");
    private final static QName _SayHelloCLASSResponse_QNAME = new QName("http://webs.classengine.arima.com/", "sayHelloCLASSResponse");
    private final static QName _GetNearestGlobalProfilesResponse_QNAME = new QName("http://webs.classengine.arima.com/", "getNearestGlobalProfilesResponse");
    private final static QName _GetNearestLocalProfiles_QNAME = new QName("http://webs.classengine.arima.com/", "getNearestLocalProfiles");
    private final static QName _GetStudentBasedStandardResponse_QNAME = new QName("http://webs.classengine.arima.com/", "getStudentBasedStandardResponse");
    private final static QName _SayHelloCLASS_QNAME = new QName("http://webs.classengine.arima.com/", "sayHelloCLASS");
    private final static QName _InsertStudents_QNAME = new QName("http://webs.classengine.arima.com/", "insertStudents");
    private final static QName _InsertStudentsResponse_QNAME = new QName("http://webs.classengine.arima.com/", "insertStudentsResponse");
    private final static QName _InsertExamPerformance_QNAME = new QName("http://webs.classengine.arima.com/", "insertExamPerformance");
    private final static QName _GetNearestLocalProfilesResponse_QNAME = new QName("http://webs.classengine.arima.com/", "getNearestLocalProfilesResponse");
    private final static QName _GetStudentBasedStandard_QNAME = new QName("http://webs.classengine.arima.com/", "getStudentBasedStandard");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.arima.classengine.webs
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link InsertExamPerformanceResponse }
     * 
     */
    public InsertExamPerformanceResponse createInsertExamPerformanceResponse() {
        return new InsertExamPerformanceResponse();
    }

    /**
     * Create an instance of {@link GetNearestGlobalProfiles }
     * 
     */
    public GetNearestGlobalProfiles createGetNearestGlobalProfiles() {
        return new GetNearestGlobalProfiles();
    }

    /**
     * Create an instance of {@link SayHelloCLASSResponse }
     * 
     */
    public SayHelloCLASSResponse createSayHelloCLASSResponse() {
        return new SayHelloCLASSResponse();
    }



    /**
     * Create an instance of {@link GetNearestLocalProfiles }
     * 
     */
    public GetNearestLocalProfiles createGetNearestLocalProfiles() {
        return new GetNearestLocalProfiles();
    }

    /**
     * Create an instance of {@link GetNearestGlobalProfilesResponse }
     * 
     */
    public GetNearestGlobalProfilesResponse createGetNearestGlobalProfilesResponse() {
        return new GetNearestGlobalProfilesResponse();
    }

    /**
     * Create an instance of {@link InsertStudentsResponse }
     * 
     */
    public InsertStudentsResponse createInsertStudentsResponse() {
        return new InsertStudentsResponse();
    }

    /**
     * Create an instance of {@link InsertExamPerformance }
     * 
     */
    public InsertExamPerformance createInsertExamPerformance() {
        return new InsertExamPerformance();
    }

    /**
     * Create an instance of {@link SayHelloCLASS }
     * 
     */
    public SayHelloCLASS createSayHelloCLASS() {
        return new SayHelloCLASS();
    }

    /**
     * Create an instance of {@link InsertStudents }
     * 
     */
    public InsertStudents createInsertStudents() {
        return new InsertStudents();
    }



    /**
     * Create an instance of {@link GetNearestLocalProfilesResponse }
     * 
     */
    public GetNearestLocalProfilesResponse createGetNearestLocalProfilesResponse() {
        return new GetNearestLocalProfilesResponse();
    }

    /**
     * Create an instance of {@link ExamStandard }
     * 
     */
    public ExamStandard createExamStandard() {
        return new ExamStandard();
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link GetNearestGlobalProfiles }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webs.classengine.arima.com/", name = "getNearestGlobalProfiles")
    public JAXBElement<GetNearestGlobalProfiles> createGetNearestGlobalProfiles(GetNearestGlobalProfiles value) {
        return new JAXBElement<GetNearestGlobalProfiles>(_GetNearestGlobalProfiles_QNAME, GetNearestGlobalProfiles.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link InsertExamPerformanceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webs.classengine.arima.com/", name = "insertExamPerformanceResponse")
    public JAXBElement<InsertExamPerformanceResponse> createInsertExamPerformanceResponse(InsertExamPerformanceResponse value) {
        return new JAXBElement<InsertExamPerformanceResponse>(_InsertExamPerformanceResponse_QNAME, InsertExamPerformanceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link SayHelloCLASSResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webs.classengine.arima.com/", name = "sayHelloCLASSResponse")
    public JAXBElement<SayHelloCLASSResponse> createSayHelloCLASSResponse(SayHelloCLASSResponse value) {
        return new JAXBElement<SayHelloCLASSResponse>(_SayHelloCLASSResponse_QNAME, SayHelloCLASSResponse.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link GetNearestGlobalProfilesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webs.classengine.arima.com/", name = "getNearestGlobalProfilesResponse")
    public JAXBElement<GetNearestGlobalProfilesResponse> createGetNearestGlobalProfilesResponse(GetNearestGlobalProfilesResponse value) {
        return new JAXBElement<GetNearestGlobalProfilesResponse>(_GetNearestGlobalProfilesResponse_QNAME, GetNearestGlobalProfilesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link GetNearestLocalProfiles }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webs.classengine.arima.com/", name = "getNearestLocalProfiles")
    public JAXBElement<GetNearestLocalProfiles> createGetNearestLocalProfiles(GetNearestLocalProfiles value) {
        return new JAXBElement<GetNearestLocalProfiles>(_GetNearestLocalProfiles_QNAME, GetNearestLocalProfiles.class, null, value);
    }



    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link SayHelloCLASS }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webs.classengine.arima.com/", name = "sayHelloCLASS")
    public JAXBElement<SayHelloCLASS> createSayHelloCLASS(SayHelloCLASS value) {
        return new JAXBElement<SayHelloCLASS>(_SayHelloCLASS_QNAME, SayHelloCLASS.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link InsertStudents }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webs.classengine.arima.com/", name = "insertStudents")
    public JAXBElement<InsertStudents> createInsertStudents(InsertStudents value) {
        return new JAXBElement<InsertStudents>(_InsertStudents_QNAME, InsertStudents.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link InsertStudentsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webs.classengine.arima.com/", name = "insertStudentsResponse")
    public JAXBElement<InsertStudentsResponse> createInsertStudentsResponse(InsertStudentsResponse value) {
        return new JAXBElement<InsertStudentsResponse>(_InsertStudentsResponse_QNAME, InsertStudentsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link InsertExamPerformance }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webs.classengine.arima.com/", name = "insertExamPerformance")
    public JAXBElement<InsertExamPerformance> createInsertExamPerformance(InsertExamPerformance value) {
        return new JAXBElement<InsertExamPerformance>(_InsertExamPerformance_QNAME, InsertExamPerformance.class, null, value);
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link GetNearestLocalProfilesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webs.classengine.arima.com/", name = "getNearestLocalProfilesResponse")
    public JAXBElement<GetNearestLocalProfilesResponse> createGetNearestLocalProfilesResponse(GetNearestLocalProfilesResponse value) {
        return new JAXBElement<GetNearestLocalProfilesResponse>(_GetNearestLocalProfilesResponse_QNAME, GetNearestLocalProfilesResponse.class, null, value);
    }



}
