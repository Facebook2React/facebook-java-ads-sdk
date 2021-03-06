/**
 * Copyright (c) 2015-present, Facebook, Inc. All rights reserved.
 *
 * You are hereby granted a non-exclusive, worldwide, royalty-free license to
 * use, copy, modify, and distribute this software in source code or binary
 * form for use in connection with the web services and APIs provided by
 * Facebook.
 *
 * As with any software that integrates with the Facebook platform, your use
 * of this software is subject to the Facebook Developer Principles and
 * Policies [http://developers.facebook.com/policy/]. This copyright notice
 * shall be included in all copies or substantial portions of the software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 *
 */

package com.facebook.ads.sdk;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.IllegalArgumentException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonParseException;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;


public class AdCreativeLinkData extends APINode {
  @SerializedName("link")
  private String mLink = null;
  @SerializedName("message")
  private String mMessage = null;
  @SerializedName("name")
  private String mName = null;
  @SerializedName("caption")
  private String mCaption = null;
  @SerializedName("attachment_style")
  private EnumAttachmentStyle mAttachmentStyle = null;
  @SerializedName("description")
  private String mDescription = null;
  @SerializedName("image_hash")
  private String mImageHash = null;
  @SerializedName("call_to_action")
  private AdCreativeLinkDataCallToAction mCallToAction = null;
  @SerializedName("image_crops")
  private AdsImageCrops mImageCrops = null;
  @SerializedName("picture")
  private String mPicture = null;
  @SerializedName("child_attachments")
  private List<AdCreativeLinkDataChildAttachment> mChildAttachments = null;
  @SerializedName("multi_share_end_card")
  private Boolean mMultiShareEndCard = null;
  @SerializedName("multi_share_optimized")
  private Boolean mMultiShareOptimized = null;
  @SerializedName("max_product_count")
  private Long mMaxProductCount = null;
  @SerializedName("additional_image_index")
  private Long mAdditionalImageIndex = null;
  @SerializedName("app_link_spec")
  private AdCreativeLinkDataAppLinkSpec mAppLinkSpec = null;
  @SerializedName("event_id")
  private String mEventId = null;
  @SerializedName("canvas_enabled")
  private Boolean mCanvasEnabled = null;
  protected static Gson gson = null;

  public AdCreativeLinkData() {
  }

  public String getId() {
    return null;
  }
  public static AdCreativeLinkData loadJSON(String json, APIContext context) {
    AdCreativeLinkData adCreativeLinkData = getGson().fromJson(json, AdCreativeLinkData.class);
    if (context.isDebug()) {
      JsonParser parser = new JsonParser();
      JsonElement o1 = parser.parse(json);
      JsonElement o2 = parser.parse(adCreativeLinkData.toString());
      if (o1.getAsJsonObject().get("__fb_trace_id__") != null) {
        o2.getAsJsonObject().add("__fb_trace_id__", o1.getAsJsonObject().get("__fb_trace_id__"));
      }
      if(!o1.equals(o2)) {
        context.log("[Warning] When parsing response, object is not consistent with JSON:");
        context.log("[JSON]" + o1);
        context.log("[Object]" + o2);
      };
    }
    adCreativeLinkData.mContext = context;
    adCreativeLinkData.rawValue = json;
    return adCreativeLinkData;
  }

  public static APINodeList<AdCreativeLinkData> parseResponse(String json, APIContext context, APIRequest request) {
    APINodeList<AdCreativeLinkData> adCreativeLinkDatas = new APINodeList<AdCreativeLinkData>(request, json);
    JsonArray arr;
    JsonObject obj;
    JsonParser parser = new JsonParser();
    try{
      JsonElement result = parser.parse(json);
      if (result.isJsonArray()) {
        // First, check if it's a pure JSON Array
        arr = result.getAsJsonArray();
        for (int i = 0; i < arr.size(); i++) {
          adCreativeLinkDatas.add(loadJSON(arr.get(i).getAsJsonObject().toString(), context));
        };
        return adCreativeLinkDatas;
      } else if (result.isJsonObject()) {
        obj = result.getAsJsonObject();
        if (obj.has("data")) {
          try {
            JsonObject paging = obj.get("paging").getAsJsonObject().get("cursors").getAsJsonObject();
            adCreativeLinkDatas.setPaging(paging.get("before").getAsString(), paging.get("after").getAsString());
          } catch (Exception ignored) {
          }
          if (obj.get("data").isJsonArray()) {
            // Second, check if it's a JSON array with "data"
            arr = obj.get("data").getAsJsonArray();
            for (int i = 0; i < arr.size(); i++) {
              adCreativeLinkDatas.add(loadJSON(arr.get(i).getAsJsonObject().toString(), context));
            };
          } else if (obj.get("data").isJsonObject()) {
            // Third, check if it's a JSON object with "data"
            obj = obj.get("data").getAsJsonObject();
            adCreativeLinkDatas.add(loadJSON(obj.toString(), context));
          }
          return adCreativeLinkDatas;
        } else if (obj.has("images")) {
          // Fourth, check if it's a map of image objects
          obj = obj.get("images").getAsJsonObject();
          for (Map.Entry<String, JsonElement> entry : obj.entrySet()) {
              adCreativeLinkDatas.add(loadJSON(entry.getValue().toString(), context));
          }
          return adCreativeLinkDatas;
        } else {
          // Fifth, check if it's pure JsonObject
          adCreativeLinkDatas.add(loadJSON(json, context));
          return adCreativeLinkDatas;
        }
      }
    } catch (Exception e) {
    }
    return null;
  }

  @Override
  public APIContext getContext() {
    return mContext;
  }

  @Override
  public void setContext(APIContext context) {
    mContext = context;
  }

  @Override
  public String toString() {
    return getGson().toJson(this);
  }


  public String getFieldLink() {
    return mLink;
  }

  public AdCreativeLinkData setFieldLink(String value) {
    this.mLink = value;
    return this;
  }

  public String getFieldMessage() {
    return mMessage;
  }

  public AdCreativeLinkData setFieldMessage(String value) {
    this.mMessage = value;
    return this;
  }

  public String getFieldName() {
    return mName;
  }

  public AdCreativeLinkData setFieldName(String value) {
    this.mName = value;
    return this;
  }

  public String getFieldCaption() {
    return mCaption;
  }

  public AdCreativeLinkData setFieldCaption(String value) {
    this.mCaption = value;
    return this;
  }

  public EnumAttachmentStyle getFieldAttachmentStyle() {
    return mAttachmentStyle;
  }

  public AdCreativeLinkData setFieldAttachmentStyle(EnumAttachmentStyle value) {
    this.mAttachmentStyle = value;
    return this;
  }

  public String getFieldDescription() {
    return mDescription;
  }

  public AdCreativeLinkData setFieldDescription(String value) {
    this.mDescription = value;
    return this;
  }

  public String getFieldImageHash() {
    return mImageHash;
  }

  public AdCreativeLinkData setFieldImageHash(String value) {
    this.mImageHash = value;
    return this;
  }

  public AdCreativeLinkDataCallToAction getFieldCallToAction() {
    return mCallToAction;
  }

  public AdCreativeLinkData setFieldCallToAction(AdCreativeLinkDataCallToAction value) {
    this.mCallToAction = value;
    return this;
  }

  public AdCreativeLinkData setFieldCallToAction(String value) {
    Type type = new TypeToken<AdCreativeLinkDataCallToAction>(){}.getType();
    this.mCallToAction = AdCreativeLinkDataCallToAction.getGson().fromJson(value, type);
    return this;
  }
  public AdsImageCrops getFieldImageCrops() {
    return mImageCrops;
  }

  public AdCreativeLinkData setFieldImageCrops(AdsImageCrops value) {
    this.mImageCrops = value;
    return this;
  }

  public AdCreativeLinkData setFieldImageCrops(String value) {
    Type type = new TypeToken<AdsImageCrops>(){}.getType();
    this.mImageCrops = AdsImageCrops.getGson().fromJson(value, type);
    return this;
  }
  public String getFieldPicture() {
    return mPicture;
  }

  public AdCreativeLinkData setFieldPicture(String value) {
    this.mPicture = value;
    return this;
  }

  public List<AdCreativeLinkDataChildAttachment> getFieldChildAttachments() {
    return mChildAttachments;
  }

  public AdCreativeLinkData setFieldChildAttachments(List<AdCreativeLinkDataChildAttachment> value) {
    this.mChildAttachments = value;
    return this;
  }

  public AdCreativeLinkData setFieldChildAttachments(String value) {
    Type type = new TypeToken<List<AdCreativeLinkDataChildAttachment>>(){}.getType();
    this.mChildAttachments = AdCreativeLinkDataChildAttachment.getGson().fromJson(value, type);
    return this;
  }
  public Boolean getFieldMultiShareEndCard() {
    return mMultiShareEndCard;
  }

  public AdCreativeLinkData setFieldMultiShareEndCard(Boolean value) {
    this.mMultiShareEndCard = value;
    return this;
  }

  public Boolean getFieldMultiShareOptimized() {
    return mMultiShareOptimized;
  }

  public AdCreativeLinkData setFieldMultiShareOptimized(Boolean value) {
    this.mMultiShareOptimized = value;
    return this;
  }

  public Long getFieldMaxProductCount() {
    return mMaxProductCount;
  }

  public AdCreativeLinkData setFieldMaxProductCount(Long value) {
    this.mMaxProductCount = value;
    return this;
  }

  public Long getFieldAdditionalImageIndex() {
    return mAdditionalImageIndex;
  }

  public AdCreativeLinkData setFieldAdditionalImageIndex(Long value) {
    this.mAdditionalImageIndex = value;
    return this;
  }

  public AdCreativeLinkDataAppLinkSpec getFieldAppLinkSpec() {
    return mAppLinkSpec;
  }

  public AdCreativeLinkData setFieldAppLinkSpec(AdCreativeLinkDataAppLinkSpec value) {
    this.mAppLinkSpec = value;
    return this;
  }

  public AdCreativeLinkData setFieldAppLinkSpec(String value) {
    Type type = new TypeToken<AdCreativeLinkDataAppLinkSpec>(){}.getType();
    this.mAppLinkSpec = AdCreativeLinkDataAppLinkSpec.getGson().fromJson(value, type);
    return this;
  }
  public String getFieldEventId() {
    return mEventId;
  }

  public AdCreativeLinkData setFieldEventId(String value) {
    this.mEventId = value;
    return this;
  }

  public Boolean getFieldCanvasEnabled() {
    return mCanvasEnabled;
  }

  public AdCreativeLinkData setFieldCanvasEnabled(Boolean value) {
    this.mCanvasEnabled = value;
    return this;
  }



  public static enum EnumAttachmentStyle {
    @SerializedName("link")
    VALUE_LINK("link"),
    @SerializedName("default")
    VALUE_DEFAULT("default"),
    NULL(null);

    private String value;

    private EnumAttachmentStyle(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return value;
    }
  }

  synchronized /*package*/ static Gson getGson() {
    if (gson != null) {
      return gson;
    } else {
      gson = new GsonBuilder()
        .excludeFieldsWithModifiers(Modifier.STATIC)
        .excludeFieldsWithModifiers(Modifier.PROTECTED)
        .disableHtmlEscaping()
        .create();
    }
    return gson;
  }

  public AdCreativeLinkData copyFrom(AdCreativeLinkData instance) {
    this.mLink = instance.mLink;
    this.mMessage = instance.mMessage;
    this.mName = instance.mName;
    this.mCaption = instance.mCaption;
    this.mAttachmentStyle = instance.mAttachmentStyle;
    this.mDescription = instance.mDescription;
    this.mImageHash = instance.mImageHash;
    this.mCallToAction = instance.mCallToAction;
    this.mImageCrops = instance.mImageCrops;
    this.mPicture = instance.mPicture;
    this.mChildAttachments = instance.mChildAttachments;
    this.mMultiShareEndCard = instance.mMultiShareEndCard;
    this.mMultiShareOptimized = instance.mMultiShareOptimized;
    this.mMaxProductCount = instance.mMaxProductCount;
    this.mAdditionalImageIndex = instance.mAdditionalImageIndex;
    this.mAppLinkSpec = instance.mAppLinkSpec;
    this.mEventId = instance.mEventId;
    this.mCanvasEnabled = instance.mCanvasEnabled;
    this.mContext = instance.mContext;
    this.rawValue = instance.rawValue;
    return this;
  }

  public static APIRequest.ResponseParser<AdCreativeLinkData> getParser() {
    return new APIRequest.ResponseParser<AdCreativeLinkData>() {
      public APINodeList<AdCreativeLinkData> parseResponse(String response, APIContext context, APIRequest<AdCreativeLinkData> request) {
        return AdCreativeLinkData.parseResponse(response, context, request);
      }
    };
  }
}