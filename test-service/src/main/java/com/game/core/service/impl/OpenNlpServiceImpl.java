package com.game.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.game.core.service.OpenNlpService;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.util.PropertiesUtils;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

import edu.stanford.nlp.trees.*;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaolianjie
 * @date 2023年01月11日 16:19
 */
public class OpenNlpServiceImpl implements OpenNlpService {
    private static String text = "今天星期五.13638693579大家都在干嘛呢都还好么狗子加微信么加VX18岁未成年，大家都好！你他么的？";
    private static String text1 = "Java is a programming. language and computing platform. first released by Sun Microsystems in 1995. ";

    @Override
    public String testNlp(String str) {


        return null;
    }

    public static void main(String[] args) throws IOException {
        sentenceDetectionTest();
        //posTagTest();
    }

    public static void sentenceDetectionTest() throws IOException {
//        Path path = Paths.get("D:\\Idea\\sprint-test\\test-service\\src\\main\\resources\\en-sent.bin");
//        System.out.println(path);
//        InputStream is = new FileInputStream(path.toFile());
//        SentenceModel model = new SentenceModel(is);
//        SentenceDetectorME sdetector = new SentenceDetectorME(model);
//        String[] sentences = sdetector.sentDetect(text1);
//        Span[] sentences1 = sdetector.sentPosDetect(text1);
//        double[] pros = sdetector.getSentenceProbabilities();
//        for (double pro : pros) {
//            System.out.println(pro);
//        }
//        for (Span sentence : sentences1) {
//            System.out.println(sentence);
//        }


        System.out.println(JSON.toJSONString(segInCh(text)));
    }




    public static List<String> segInCh(String text) {
        //载入properties 文件
//        StanfordCoreNLP pipline = new StanfordCoreNLP("StanfordCoreNLP-chinese.properties");

        //1.2 自定义功能 （1）
        //StanfordCoreNLP的各个组件（annotator）按“tokenize（分词）, ssplit（断句）, pos（词性标注）, lemma（词元化）,
        // ner（命名实体识别）, parse（语法分析）, dcoref（同义词分辨）”顺序进行处理。
//        Properties properties = new Properties();
//        properties.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
//        StanfordCoreNLP pipline = new StanfordCoreNLP(properties);

        //自定义功能(2) 自己在项目中建一个properties 文件，然后在文件中设置模型属性，可以参考1中的配置文件
//        String[] args = new String[]{"-props", "properies/CoreNLP-Seg-CH.properties"};
//        Properties properties = StringUtils.argsToProperties(args);
//        StanfordCoreNLP pipline = new StanfordCoreNLP(properties);

        //自定义功能(3)
        StanfordCoreNLP pipline = new StanfordCoreNLP(PropertiesUtils.asProperties(
                "annotators", "tokenize,ssplit,pos,lemma,ner,parse,dcoref",
                "ssplit.isOneSentence", "true",
                "tokenize.language", "zh",
                "segment.model", "edu/stanford/nlp/models/segmenter/chinese/ctb.gz",
                "segment.sighanCorporaDict", "edu/stanford/nlp/models/segmenter/chinese",
                "segment.serDictionary", "edu/stanford/nlp/models/segmenter/chinese/dict-chris6.ser.gz",
                "segment.sighanPostProcessing", "true"
        ));
        //创建一个解析器，传入的是需要解析的文本
        Annotation annotation = new Annotation(text);
        //解析
        pipline.annotate(annotation);
        //根据标点符号，进行句子的切分，每一个句子被转化为一个CoreMap的数据结构，保存了句子的信息()
        List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
        //从CoreMap 中取出CoreLabel List ,打印
        ArrayList<String> list = new ArrayList<>();
        for (CoreMap sentence : sentences) {
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                String word = token.get(CoreAnnotations.TextAnnotation.class);
                // this is the POS tag of the token
                String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
                // this is the NER label of the token
                String lemma = token.get(CoreAnnotations.LemmaAnnotation.class);

                String ne = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);
                String result = word;
                list.add(result);
            }
        }
        return list;
    }


    public static void posTagTest() throws IOException {
        String text = "Java is a programming language and computing platform first released by Sun Microsystems in 1995. ";
        Path path = Paths.get("D:\\Idea\\sprint-test\\test-service\\src\\main\\resources\\en-sent.bin");
        InputStream is = new FileInputStream(path.toFile());
        SentenceDetectorME sdetector = new SentenceDetectorME(new SentenceModel(is));
        String[] sentences = sdetector.sentDetect(text);
        for (String sentence : sentences) {
            Path tokenPath = Paths.get("D:\\Idea\\sprint-test\\test-service\\src\\main\\resources\\en-token.bin");
            InputStream tokeInputStream = new FileInputStream(tokenPath.toFile());
            TokenizerME tokenizer = new TokenizerME(new TokenizerModel(tokeInputStream));
            String[] tokens = tokenizer.tokenize(sentence);
            Path posPath = Paths.get("D:\\Idea\\sprint-test\\test-service\\src\\main\\resources\\en-pos-perceptron.bin");
            InputStream posInputStream = new FileInputStream(posPath.toFile());
            POSModel posModel = new POSModel(posInputStream);
            POSTaggerME posTagger = new POSTaggerME(posModel);
            String[] tagArray = posTagger.tag(tokens);
            for (int i = 0; i < tokens.length; i++) {
                System.out.printf("%s -- %s%n", tokens[i], tagArray[i]);
            }
        }
    }

}
