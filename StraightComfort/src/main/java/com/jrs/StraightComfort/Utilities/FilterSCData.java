package com.jrs.StraightComfort.Utilities;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.util.Log;
import android.util.Xml;
import android.widget.ImageView;

import com.jrs.StraightComfort.Views.Discomfort;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;

public class FilterSCData {

    private static final String ns = null;
    private static FilterSCData instance;
    public boolean isInit = false;

    private static final XmlPullParser parseDisconfortXml = Xml.newPullParser();
    private static final XmlPullParser parseScInfoXml = Xml.newPullParser();
    private static final ArrayList<Content> contents = new ArrayList<Content>();
    private ArrayList<DiscomfortInfo> discomfortcontents = new ArrayList<DiscomfortInfo>();

    private FilterSCData(){}

    public static FilterSCData getInstance() {
        if (instance == null)
            instance = new FilterSCData();
        return instance;
    }

    public void init(Context context) throws XmlPullParserException, IOException {
        try {
            AssetManager assets = context.getResources().getAssets();
            InputStream in_xml = assets.open("discomfortInfo.xml");
            parseDisconfortXml.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parseDisconfortXml.setInput(in_xml, null);
            in_xml = assets.open("scInfo.xml");
            parseScInfoXml.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parseScInfoXml.setInput(in_xml, null);
            //in_xml.close();

        } catch (FileNotFoundException e) {
            Log.e("Npt Found", e.toString());
        } catch (NullPointerException f) {
            Log.e("Not Found", f.toString());
        }

    }
    public void getDiscomfortInfo() throws XmlPullParserException, IOException
    {
        XmlPullParser parser = parseDisconfortXml;
        Bodypart title  = null;
        String name = "";

        parser.nextTag();
        parser.require(XmlPullParser.START_TAG, ns, "Discomfort");

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            name = parser.getName();
            if (name.equals("Bodypart"))
            {
                ArrayList<SolutionInfo> solutions = new ArrayList<SolutionInfo>();
                parser.require(XmlPullParser.START_TAG, ns, "Bodypart");
                ArrayList<Page> pages = new ArrayList<Page>();
                while (parser.next() != XmlPullParser.END_TAG) {
                    if (parser.getEventType() != XmlPullParser.START_TAG) {
                        continue;
                    }

                    name = parser.getName();
                    if (name.equalsIgnoreCase("Bodypartname")) {
                       title = new Bodypart(readTagName(parser,"Bodypartname"));
                    }
                    else if (name.equals("STag")) {
                        solutions.add(getSolution(parser));
                    }
                    else {
                        skip(parser);
                    }
                }
                discomfortcontents.add(new DiscomfortInfo(title,solutions));
            }
            else {
                skip(parser);
            }
        }
    }

    public SolutionInfo getSolution(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, "STag");

        String title = "";
        String name = "";
        ArrayList<Integer> pages = new ArrayList<Integer>();
        pages.clear();
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }

            name = parser.getName();
            try {

                if (name.equals("Nametag")) {
                    title = readTagName(parser, "Nametag");
                } else if (name.equals("Page")) {
                    pages.add(Integer.parseInt(readTagName(parser, "Page")));
                }
                else {
                    skip(parser);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
            return new SolutionInfo(title,pages);
    }
    public void getPageInfo(Context context) throws XmlPullParserException, IOException
    {
        XmlPullParser parser = parseScInfoXml;
        String title = "";
        String name = "";
        String icon = "";
        Integer iconResource = 0;
        Drawable myIcon = null;
        parser.nextTag();
        parser.require(XmlPullParser.START_TAG, ns, "Shortcutinfo");
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            if (parser.getName().equals("Shortcut"))
            {
                parser.require(XmlPullParser.START_TAG, ns, "Shortcut");
                ArrayList<Page> pages = new ArrayList<Page>();
                while (parser.next() != XmlPullParser.END_TAG) {
                    if (parser.getEventType() != XmlPullParser.START_TAG) {
                        continue;
                    }

                    name = parser.getName();
                    if (name.equalsIgnoreCase("Nametag")) {
                        title = readTagName(parser,"Nametag");
                    }
                    else if (name.equals("Icon")) {
                        icon = readTagName(parser, "Icon");
                        iconResource = context.getApplicationContext().getResources().getIdentifier(icon, "drawable", context.getPackageName());
                        ImageView image = new ImageView(context);
                        image.setImageResource(iconResource);
                        myIcon = context.getResources().getDrawable(iconResource);
                    }
                    else if (name.equalsIgnoreCase("Page"))
                    {
                        pages.add(getPage(parser,context));
                    }
                    else {
                        skip(parser);
                    }
                }
               contents.add(new Content(title,myIcon,pages));
            }
            else {
                skip(parser);
            }
        }
    }


    public Page getPage(XmlPullParser parser,Context context) throws XmlPullParserException, IOException
    {
        parser.require(XmlPullParser.START_TAG, ns, "Page");
        String number = "";
        String image = "";
        String text = "";
        String name = "";
        while (parser.next()!= XmlPullParser.END_TAG)
        {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }

            name = parser.getName();
            try {

                if (name.equals("Number")) {
                    number = readTagName(parser, "Number");
                }
                else if (name.equals("Image")) {
                    image = readTagName(parser, "Image");
                }
                else if (name.equals("Text")) {
                    text = readTagName(parser, "Text");
                } else {
                    skip(parser);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        }

        return new Page(image,text,Integer.parseInt(number));
    }
    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }
    private String readTagName(XmlPullParser parser, String tagname) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, tagname);
        String title = parser.nextText();


        parser.require(XmlPullParser.END_TAG, ns, tagname);
        return title;
    }
    public ArrayList<Content> getContents()
    {
        return contents;
    }
    public ArrayList<DiscomfortInfo> getDiscomfortcontents()
    {
        return discomfortcontents;
    }
    public void setDiscomfortcontents(ArrayList<DiscomfortInfo> discomfort)
    {
        discomfortcontents = discomfort;
    }


}