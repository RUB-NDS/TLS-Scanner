/**
 * TLS-Scanner - A TLS configuration and analysis tool based on TLS-Attacker.
 * <p>
 * Copyright 2017-2019 Ruhr University Bochum / Hackmanit GmbH
 * <p>
 * Licensed under Apache License 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
package de.rub.nds.tlsscanner.serverscanner.report.result;

import de.rub.nds.tlsattacker.core.certificate.transparency.SignedCertificateTimestampList;
import de.rub.nds.tlsscanner.serverscanner.constants.ProbeType;
import de.rub.nds.tlsscanner.serverscanner.report.AnalyzedProperty;
import de.rub.nds.tlsscanner.serverscanner.report.SiteReport;
import org.bouncycastle.crypto.tls.Certificate;

public class CertificateTransparencyResult extends ProbeResult {

    private boolean supportsPrecertificateSCTs;
    private boolean supportsHandshakeSCTs;
    private boolean supportsOcspSCTs;
    private boolean meetsChromeCTPolicy;
    private SignedCertificateTimestampList precertificateSctList;
    private SignedCertificateTimestampList handshakeSctList;
    private SignedCertificateTimestampList ocspSctList;

    public CertificateTransparencyResult(boolean supportsPrecertificateSCTs,
                                         boolean supportsHandshakeSCTs, boolean supportsOcspSCTs,
                                         boolean meetsChromeCTPolicy,
                                         SignedCertificateTimestampList precertificateSctList,
                                         SignedCertificateTimestampList handshakeSctList,
                                         SignedCertificateTimestampList ocspSctList) {
        super(ProbeType.CERTIFICATE_TRANSPARENCY);
        this.supportsPrecertificateSCTs = supportsPrecertificateSCTs;
        this.supportsHandshakeSCTs = supportsHandshakeSCTs;
        this.supportsOcspSCTs = supportsOcspSCTs;
        this.meetsChromeCTPolicy = meetsChromeCTPolicy;
        this.precertificateSctList = precertificateSctList;
        this.handshakeSctList = handshakeSctList;
        this.ocspSctList = ocspSctList;

    }

    @Override
    protected void mergeData(SiteReport report) {
        report.setPrecertificateSctList(precertificateSctList);
        report.setHandshakeSctList(handshakeSctList);
        report.setOcspSctList(ocspSctList);

        report.putResult(AnalyzedProperty.SUPPORTS_SCTS_PRECERTIFICATE, supportsPrecertificateSCTs);
        report.putResult(AnalyzedProperty.SUPPORTS_SCTS_HANDSHAKE, supportsHandshakeSCTs);
        report.putResult(AnalyzedProperty.SUPPORTS_SCTS_OCSP, supportsOcspSCTs);
        report.putResult(AnalyzedProperty.SUPPORTS_CHROME_CT_POLICY, meetsChromeCTPolicy);
    }
}
