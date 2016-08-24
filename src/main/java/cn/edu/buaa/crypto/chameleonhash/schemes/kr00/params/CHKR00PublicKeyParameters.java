package cn.edu.buaa.crypto.chameleonhash.schemes.kr00.params;

import cn.edu.buaa.crypto.algebra.PairingUtils;
import cn.edu.buaa.crypto.chameleonhash.params.ChameleonHashPublicKeyParameters;
import cn.edu.buaa.crypto.chameleonhash.schemes.kr00.CHKR00Engine;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.PairingParameters;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by Weiran Liu on 2016/4/5.
 */
public class CHKR00PublicKeyParameters extends ChameleonHashPublicKeyParameters {
    private final Element g;
    private final Element y;

    public CHKR00PublicKeyParameters(PairingParameters pairingParameters, Element g, Element y) {
        super(pairingParameters);
        this.g = g.getImmutable();
        this.y = y.getImmutable();
    }

    public Element getG() { return this.g.duplicate(); }

    public Element getY() { return this.y.duplicate(); }

    @Override
    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }
        if (anObject instanceof CHKR00PublicKeyParameters) {
            CHKR00PublicKeyParameters that = (CHKR00PublicKeyParameters)anObject;
            //Compare y
            if (!PairingUtils.isEqualElement(this.y, that.getY())) {
                return false;
            }
            //Compare Pairing Parameters
            return this.getParameters().toString().equals(that.getParameters().toString());
        }
        return false;
    }

    @Override
    public byte[] toBytes() {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byteArrayOutputStream.write(this.g.toBytes());
            byteArrayOutputStream.write(this.y.toBytes());
            byte[] resultBytes = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            return resultBytes;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getCHEngineName() {
        return CHKR00Engine.SCHEME_NAME;
    }
}
