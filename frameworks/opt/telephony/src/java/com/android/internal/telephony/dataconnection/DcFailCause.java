/* Copyright Statement:
 *
 * This software/firmware and related documentation ("MediaTek Software") are
 * protected under relevant copyright laws. The information contained herein
 * is confidential and proprietary to MediaTek Inc. and/or its licensors.
 * Without the prior written permission of MediaTek inc. and/or its licensors,
 * any reproduction, modification, use or disclosure of MediaTek Software,
 * and information contained herein, in whole or in part, shall be strictly prohibited.
 */
/* MediaTek Inc. (C) 2017. All rights reserved.
 *
 * BY OPENING THIS FILE, RECEIVER HEREBY UNEQUIVOCALLY ACKNOWLEDGES AND AGREES
 * THAT THE SOFTWARE/FIRMWARE AND ITS DOCUMENTATIONS ("MEDIATEK SOFTWARE")
 * RECEIVED FROM MEDIATEK AND/OR ITS REPRESENTATIVES ARE PROVIDED TO RECEIVER ON
 * AN "AS-IS" BASIS ONLY. MEDIATEK EXPRESSLY DISCLAIMS ANY AND ALL WARRANTIES,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR NONINFRINGEMENT.
 * NEITHER DOES MEDIATEK PROVIDE ANY WARRANTY WHATSOEVER WITH RESPECT TO THE
 * SOFTWARE OF ANY THIRD PARTY WHICH MAY BE USED BY, INCORPORATED IN, OR
 * SUPPLIED WITH THE MEDIATEK SOFTWARE, AND RECEIVER AGREES TO LOOK ONLY TO SUCH
 * THIRD PARTY FOR ANY WARRANTY CLAIM RELATING THERETO. RECEIVER EXPRESSLY ACKNOWLEDGES
 * THAT IT IS RECEIVER'S SOLE RESPONSIBILITY TO OBTAIN FROM ANY THIRD PARTY ALL PROPER LICENSES
 * CONTAINED IN MEDIATEK SOFTWARE. MEDIATEK SHALL ALSO NOT BE RESPONSIBLE FOR ANY MEDIATEK
 * SOFTWARE RELEASES MADE TO RECEIVER'S SPECIFICATION OR TO CONFORM TO A PARTICULAR
 * STANDARD OR OPEN FORUM. RECEIVER'S SOLE AND EXCLUSIVE REMEDY AND MEDIATEK'S ENTIRE AND
 * CUMULATIVE LIABILITY WITH RESPECT TO THE MEDIATEK SOFTWARE RELEASED HEREUNDER WILL BE,
 * AT MEDIATEK'S OPTION, TO REVISE OR REPLACE THE MEDIATEK SOFTWARE AT ISSUE,
 * OR REFUND ANY SOFTWARE LICENSE FEES OR SERVICE CHARGE PAID BY RECEIVER TO
 * MEDIATEK FOR SUCH MEDIATEK SOFTWARE AT ISSUE.
 *
 * The following software/firmware and/or related documentation ("MediaTek Software")
 * have been modified by MediaTek Inc. All revisions are subject to any receiver's
 * applicable license agreements with MediaTek Inc.
 */
/*
 * Copyright (C) 2006 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.android.internal.telephony.dataconnection;

import android.content.Context;
import android.content.res.Resources;
import android.os.PersistableBundle;
import android.telephony.CarrierConfigManager;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Returned as the reason for a connection failure as defined
 * by RIL_DataCallFailCause in ril.h and some local errors.
 */
public enum DcFailCause {
    NONE(0),

    // This series of errors as specified by the standards
    // specified in ril.h
    OPERATOR_BARRED(0x08),                  /* no retry */
    NAS_SIGNALLING(0x0E),
    LLC_SNDCP(0x19),
    INSUFFICIENT_RESOURCES(0x1A),
    MISSING_UNKNOWN_APN(0x1B),              /* no retry */
    UNKNOWN_PDP_ADDRESS_TYPE(0x1C),         /* no retry */
    USER_AUTHENTICATION(0x1D),              /* no retry */
    ACTIVATION_REJECT_GGSN(0x1E),           /* no retry */
    ACTIVATION_REJECT_UNSPECIFIED(0x1F),
    SERVICE_OPTION_NOT_SUPPORTED(0x20),     /* no retry */
    SERVICE_OPTION_NOT_SUBSCRIBED(0x21),    /* no retry */
    SERVICE_OPTION_OUT_OF_ORDER(0x22),
    NSAPI_IN_USE(0x23),                     /* no retry */
    REGULAR_DEACTIVATION(0x24),             /* possibly restart radio, based on config */
    QOS_NOT_ACCEPTED(0x25),
    NETWORK_FAILURE(0x26),
    UMTS_REACTIVATION_REQ(0x27),
    FEATURE_NOT_SUPP(0x28),
    TFT_SEMANTIC_ERROR(0x29),
    TFT_SYTAX_ERROR(0x2A),
    UNKNOWN_PDP_CONTEXT(0x2B),
    FILTER_SEMANTIC_ERROR(0x2C),
    FILTER_SYTAX_ERROR(0x2D),
    PDP_WITHOUT_ACTIVE_TFT(0x2E),
    ONLY_IPV4_ALLOWED(0x32),                /* no retry */
    ONLY_IPV6_ALLOWED(0x33),                /* no retry */
    ONLY_SINGLE_BEARER_ALLOWED(0x34),
    ESM_INFO_NOT_RECEIVED(0x35),
    PDN_CONN_DOES_NOT_EXIST(0x36),
    MULTI_CONN_TO_SAME_PDN_NOT_ALLOWED(0x37),
    MAX_ACTIVE_PDP_CONTEXT_REACHED(0x41),
    UNSUPPORTED_APN_IN_CURRENT_PLMN(0x42),
    INVALID_TRANSACTION_ID(0x51),
    MESSAGE_INCORRECT_SEMANTIC(0x5F),
    INVALID_MANDATORY_INFO(0x60),
    MESSAGE_TYPE_UNSUPPORTED(0x61),
    MSG_TYPE_NONCOMPATIBLE_STATE(0x62),
    UNKNOWN_INFO_ELEMENT(0x63),
    CONDITIONAL_IE_ERROR(0x64),
    MSG_AND_PROTOCOL_STATE_UNCOMPATIBLE(0x65),
    PROTOCOL_ERRORS(0x6F),                  /* no retry */
    APN_TYPE_CONFLICT(0x70),
    INVALID_PCSCF_ADDR(0x71),
    INTERNAL_CALL_PREEMPT_BY_HIGH_PRIO_APN(0x72),
    EMM_ACCESS_BARRED(0x73),
    EMERGENCY_IFACE_ONLY(0x74),
    IFACE_MISMATCH(0x75),
    COMPANION_IFACE_IN_USE(0x76),
    IP_ADDRESS_MISMATCH(0x77),
    IFACE_AND_POL_FAMILY_MISMATCH(0x78),
    EMM_ACCESS_BARRED_INFINITE_RETRY(0x79),
    AUTH_FAILURE_ON_EMERGENCY_CALL(0x7A),

    // OEM sepecific error codes. To be used by OEMs when they don't
    // want to reveal error code which would be replaced by ERROR_UNSPECIFIED
    OEM_DCFAILCAUSE_1(0x1001),
    OEM_DCFAILCAUSE_2(0x1002),
    OEM_DCFAILCAUSE_3(0x1003),
    OEM_DCFAILCAUSE_4(0x1004),
    OEM_DCFAILCAUSE_5(0x1005),
    OEM_DCFAILCAUSE_6(0x1006),
    OEM_DCFAILCAUSE_7(0x1007),
    OEM_DCFAILCAUSE_8(0x1008),
    OEM_DCFAILCAUSE_9(0x1009),
    OEM_DCFAILCAUSE_10(0x100A),
    OEM_DCFAILCAUSE_11(0x100B),
    OEM_DCFAILCAUSE_12(0x100C),
    OEM_DCFAILCAUSE_13(0x100D),
    OEM_DCFAILCAUSE_14(0x100E),
    OEM_DCFAILCAUSE_15(0x100F),

    // Local errors generated by Vendor RIL
    // specified in ril.h
    REGISTRATION_FAIL(-1),
    GPRS_REGISTRATION_FAIL(-2),
    SIGNAL_LOST(-3),                        /* no retry */
    PREF_RADIO_TECH_CHANGED(-4),
    RADIO_POWER_OFF(-5),                    /* no retry */
    TETHERED_CALL_ACTIVE(-6),               /* no retry */
    MTK_PDP_FAIL_FALLBACK_RETRY(-1000),          /* For FALLBACK PDP Retry cause */
    MTK_DUE_TO_REACH_RETRY_COUNTER(0x0E0F),      /* no retry */
    MTK_TCM_ESM_TIMER_TIMEOUT(0x0F46),      /* no retry */
    ERROR_UNSPECIFIED(0xFFFF),

    // Errors generated by the Framework
    // specified here
    UNKNOWN(0x10000),
    RADIO_NOT_AVAILABLE(0x10001),                   /* no retry */
    UNACCEPTABLE_NETWORK_PARAMETER(0x10002),        /* no retry */
    CONNECTION_TO_DATACONNECTIONAC_BROKEN(0x10003),
    LOST_CONNECTION(0x10004),
    RESET_BY_FRAMEWORK(0x10005);

    private final int mErrorCode;
    private static final HashMap<Integer, DcFailCause> sErrorCodeToFailCauseMap;
    static {
        sErrorCodeToFailCauseMap = new HashMap<Integer, DcFailCause>();
        for (DcFailCause fc : values()) {
            sErrorCodeToFailCauseMap.put(fc.getErrorCode(), fc);
        }
    }

    /**
     * Map of subId -> set of data call setup permanent failure for the carrier.
     */
    private static final HashMap<Integer, HashSet<DcFailCause>> sPermanentFailureCache =
            new HashMap<>();

    DcFailCause(int errorCode) {
        mErrorCode = errorCode;
    }

    public int getErrorCode() {
        return mErrorCode;
    }

    /**
     * Returns whether or not the radio has failed and also needs to be restarted.
     * By default, we do not restart radio on REGULAR_DEACTIVATION.
     *
     * @param context device context
     * @param subId subscription id
     * @return true if the radio has failed and the carrier requres restart, otherwise false
     */
    public boolean isRestartRadioFail(Context context, int subId) {
        if (this == REGULAR_DEACTIVATION) {
            CarrierConfigManager configManager = (CarrierConfigManager)
                    context.getSystemService(Context.CARRIER_CONFIG_SERVICE);
            if (configManager != null) {
                PersistableBundle b = configManager.getConfigForSubId(subId);
                if (b != null) {
                    return b.getBoolean(CarrierConfigManager.
                            KEY_RESTART_RADIO_ON_PDP_FAIL_REGULAR_DEACTIVATION_BOOL);
                }
            }
        }
        return false;
    }

    public boolean isPermanentFailure(Context context, int subId) {

        synchronized (sPermanentFailureCache) {

            HashSet<DcFailCause> permanentFailureSet = sPermanentFailureCache.get(subId);

            // In case of cache miss, we need to look up the settings from carrier config.
            if (permanentFailureSet == null) {
                // Retrieve the permanent failure from carrier config
                CarrierConfigManager configManager = (CarrierConfigManager)
                        context.getSystemService(Context.CARRIER_CONFIG_SERVICE);
                if (configManager != null) {
                    PersistableBundle b = configManager.getConfigForSubId(subId);
                    if (b != null) {
                        String[] permanentFailureStrings = b.getStringArray(CarrierConfigManager.
                                KEY_CARRIER_DATA_CALL_PERMANENT_FAILURE_STRINGS);

                        if (permanentFailureStrings != null) {
                            permanentFailureSet = new HashSet<>();
                            for (String failure : permanentFailureStrings) {
                                permanentFailureSet.add(DcFailCause.valueOf(failure));
                            }
                        }
                    }
                }

                // If we are not able to find the configuration from carrier config, use the default
                // ones.
                if (permanentFailureSet == null) {
                    permanentFailureSet = new HashSet<DcFailCause>() {
                        {
                            add(OPERATOR_BARRED);
                            add(MISSING_UNKNOWN_APN);
                            add(UNKNOWN_PDP_ADDRESS_TYPE);
                            add(USER_AUTHENTICATION);
                            add(ACTIVATION_REJECT_GGSN);
                            add(SERVICE_OPTION_NOT_SUPPORTED);
                            add(SERVICE_OPTION_NOT_SUBSCRIBED);
                            add(NSAPI_IN_USE);
                            add(ONLY_IPV4_ALLOWED);
                            add(ONLY_IPV6_ALLOWED);
                            add(PROTOCOL_ERRORS);
                            add(RADIO_POWER_OFF);
                            add(TETHERED_CALL_ACTIVE);
                            add(RADIO_NOT_AVAILABLE);
                            add(UNACCEPTABLE_NETWORK_PARAMETER);
                            add(SIGNAL_LOST);
                        }
                    };
                }

                sPermanentFailureCache.put(subId, permanentFailureSet);
            }

            return permanentFailureSet.contains(this);
        }
    }

    public boolean isEventLoggable() {
        return (this == OPERATOR_BARRED) || (this == INSUFFICIENT_RESOURCES) ||
                (this == UNKNOWN_PDP_ADDRESS_TYPE) || (this == USER_AUTHENTICATION) ||
                (this == ACTIVATION_REJECT_GGSN) || (this == ACTIVATION_REJECT_UNSPECIFIED) ||
                (this == SERVICE_OPTION_NOT_SUBSCRIBED) ||
                (this == SERVICE_OPTION_NOT_SUPPORTED) ||
                (this == SERVICE_OPTION_OUT_OF_ORDER) || (this == NSAPI_IN_USE) ||
                (this == ONLY_IPV4_ALLOWED) || (this == ONLY_IPV6_ALLOWED) ||
                (this == PROTOCOL_ERRORS) || (this == SIGNAL_LOST) ||
                (this == RADIO_POWER_OFF) || (this == TETHERED_CALL_ACTIVE) ||
                (this == UNACCEPTABLE_NETWORK_PARAMETER);
    }

    public static DcFailCause fromInt(int errorCode) {
        DcFailCause fc = sErrorCodeToFailCauseMap.get(errorCode);
        if (fc == null) {
            fc = UNKNOWN;
        }
        return fc;
    }
}
