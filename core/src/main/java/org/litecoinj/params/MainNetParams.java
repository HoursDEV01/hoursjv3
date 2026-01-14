/*
 * Copyright 2013 Google Inc.
 * Copyright 2015 Andreas Schildbach
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.litecoinj.params;

import org.litecoinj.core.*;
import org.litecoinj.net.discovery.*;


import java.net.*;

import static com.google.common.base.Preconditions.*;

/**
 * Parameters for the main production network on which people trade goods and services.
 */
public class MainNetParams extends AbstractBitcoinNetParams {
    private int p2shHeader2;
    public static final int MAINNET_MAJORITY_WINDOW = 1000;
    public static final int MAINNET_MAJORITY_REJECT_BLOCK_OUTDATED = 950;
    public static final int MAINNET_MAJORITY_ENFORCE_BLOCK_UPGRADE = 750;
    
    public MainNetParams() {
        super();
        interval = INTERVAL;
        targetTimespan = TARGET_TIMESPAN;
        maxTarget = Utils.decodeCompactBits(0x1e0fffffL);
        addressHeader = 42;
        dumpedPrivateKeyHeader = 128 + addressHeader;
        p2shHeader = 5;
        p2shHeader2 = 50;

        acceptableAddressCodes = new int[] { addressHeader, p2shHeader,p2shHeader2 };
        port = 9024;
        packetMagic = 0xfac2b2db;
        bip32HeaderPub = 0x0288b11e; //The 4 byte header that serializes in base58 to "xpub".
        bip32HeaderPriv = 0x0328aee4; //The 4 byte header that serializes in base58 to "xprv"

        majorityEnforceBlockUpgrade = MAINNET_MAJORITY_ENFORCE_BLOCK_UPGRADE;
        majorityRejectBlockOutdated = MAINNET_MAJORITY_REJECT_BLOCK_OUTDATED;
        majorityWindow = MAINNET_MAJORITY_WINDOW;

        genesisBlock.setDifficultyTarget(0x1e0ffff0L);
        genesisBlock.setTime(1735612596L);
        genesisBlock.setNonce(2006126818L);
        id = ID_MAINNET;
        subsidyDecreaseBlockCount = 840000;
        spendableCoinbaseDepth = 100;
        String genesisHash = genesisBlock.getHashAsString();
        System.out.println(genesisHash);
       // checkState(genesisHash.equals("7e718b4eacbb5c606a463f3f69164296df0255e280e751a7652e14fae57eb098"),
         //      genesisHash);

        // This contains (at a minimum) the blocks which are not BIP30 compliant. BIP30 changed how duplicate
        // transactions are handled. Duplicated transactions could occur in the case where a coinbase had the same
        // extraNonce and the same outputs but appeared at different heights, and greatly complicated re-org handling.
        // Having these here simplifies block connection logic considerably.
        checkpoints.put(0, Sha256Hash.wrap("7e718b4eacbb5c606a463f3f69164296df0255e280e751a7652e14fae57eb098"));
        checkpoints.put(8572, Sha256Hash.wrap("0977687733122de382a20a001449ce9c558c11bfd6ab96c90a23d1408bafa9eb"));
        checkpoints.put(14532, Sha256Hash.wrap("b77a4f4ffa69dc43c7ebd818eec3ca74c33d3fa66e7df63c9bc2eb95c503f39e"));
        checkpoints.put(28777, Sha256Hash.wrap("3ac0831eeaa633a9c5ec6f032f2397555d3affdf1e26fa503d6f2b4ccea045ce"));
        checkpoints.put(30846, Sha256Hash.wrap("a661836395ac2f04f373ce705312aa92526ae4fd0d3eace84e908ff9f2dfabe7"));

        dnsSeeds = new String[] {
                "hour-n1.flightsystem.org",
                "hour-n2.flightsystem.org"
                
        };

        httpSeeds = new HttpDiscovery.Details[] {
                // Andreas Schildbach
//                new HttpDiscovery.Details(
//                        ECKey.fromPublicOnly(Utils.HEX.decode("0238746c59d46d5408bf8b1d0af5740fe1a6e1703fcb56b2953f0b965c740d256f")),
//                        URI.create("http://httpseed.bitcoin.schildbach.de/peers")
//                )
        };

        addrSeeds = new int[] {
//                
        };
    }

    private static MainNetParams instance;
    public static synchronized MainNetParams get() {
        if (instance == null) {
            instance = new MainNetParams();
        }
        return instance;
    }

    @Override
    public String getPaymentProtocolId() {
        return PAYMENT_PROTOCOL_ID_MAINNET;
    }
}
