package team.tourgini.moodplant;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import team.tourgini.moodplant.domain.Plant;
import team.tourgini.moodplant.domain.PlantSpaceCondition;
import team.tourgini.moodplant.domain.PlantTheme;
import team.tourgini.moodplant.domain.PlantVoiceAndTone;
import team.tourgini.moodplant.repository.GuideSpaceConditionRepository;
import team.tourgini.moodplant.repository.GuideThemeRepository;
import team.tourgini.moodplant.repository.GuideVoiceAndToneRepository;
import team.tourgini.moodplant.repository.PlantRepository;
import team.tourgini.moodplant.repository.PlantSpaceConditionRepository;
import team.tourgini.moodplant.repository.PlantThemeRepository;
import team.tourgini.moodplant.repository.PlantVoiceRepository;
import team.tourgini.moodplant.repository.SpaceConditionRepository;
import team.tourgini.moodplant.repository.ThemeRepository;
import team.tourgini.moodplant.repository.VoiceAndToneRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootTest
public class InsertRecord {

    private static final String JSON = "[\n" +
            "  {\n" +
            "    \"en\": \"Aloe Vera\",\n" +
            "    \"ko\": \"알로에 베라\",\n" +
            "    \"voice\": [\"Soothing\", \"Minimalist\", \"Calming\"],\n" +
            "    \"theme\": [\"Spring Renewal\", \"Winter Wonderland\"],\n" +
            "    \"keyword\": [\"Drought-Resistant\"],\n" +
            "    \"image\": \"https://www.almanac.com/sites/default/files/styles/or/public/image_nodes/aloe-vera-white-pot_sunwand24-ss_edit_0.jpg?itok=uOJaHAeR\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"en\": \"Spider Plant\",\n" +
            "    \"ko\": \"스파이더 플랜트\",\n" +
            "    \"voice\": [\"Welcoming\", \"Adaptable\", \"Friendly\"],\n" +
            "    \"theme\": [\"Winter Solace\", \"Winter Wonderland\"],\n" +
            "    \"keyword\": [\"Hanging Varieties\"],\n" +
            "    \"image\": \"https://www.bhg.com/thmb/oDnjlrHprd67aYvinrMfQgVUPtQ=/5332x0/filters:no_upscale():strip_icc()/BHG-spider-plant-c0e0fdd5ec6e4c1588998ce3167f6579.jpg\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"en\": \"Peace Lily\",\n" +
            "    \"ko\": \"피스 릴리\",\n" +
            "    \"voice\": [\"Elegant\", \"Calming\", \"Sophisticated\"],\n" +
            "    \"theme\": [\"Spring Renewal\", \"Valentine's Romance\"],\n" +
            "    \"image\": \"https://thewateringcan.ca/wp-content/uploads/2021/03/6-Peace-lily-scaled-1-scaled-scaled.jpg\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"en\": \"Snake Plant\",\n" +
            "    \"ko\": \"스네이크 플랜트\",\n" +
            "    \"voice\": [\"Architectural\", \"Minimalist\", \"Bold\"],\n" +
            "    \"theme\": [\"Autumn Harvest\", \"Winter Wonderland\"],\n" +
            "    \"keyword\": [\"Low Light Tolerant\"],\n" +
            "    \"image\": \"https://static.toiimg.com/photo/104371594.cms\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"en\": \"Rubber Plant\",\n" +
            "    \"ko\": \"고무나무\",\n" +
            "    \"voice\": [\"Bold\", \"Chic\", \"Confident\"],\n" +
            "    \"theme\": [\"Autumn Harvest\", \"Christmas Cheer\"],\n" +
            "    \"image\": \"https://abeautifulmess.com/wp-content/uploads/2023/06/rubbertree-1.jpg\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"en\": \"Fiddle Leaf Fig\",\n" +
            "    \"ko\": \"피들 리프 피그\",\n" +
            "    \"voice\": [\"Trendy\", \"Artistic\", \"Elegant\"],\n" +
            "    \"theme\": [\"Valentine's Romance\", \"New Year Fresh Start\"],\n" +
            "    \"image\": \"https://www.thespruce.com/thmb/lqtFsKArHjDEugR06R3k1EZHs58=/6590x0/filters:no_upscale():max_bytes(150000):strip_icc()/grow-fiddle-leaf-fig-indoors-1902756-hero-feca31e64e91430794e2bdcc9fa1e901.jpg\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"en\": \"Philodendron\",\n" +
            "    \"ko\": \"필로덴드론\",\n" +
            "    \"voice\": [\"Classic\", \"Adaptable\", \"Chic\"],\n" +
            "    \"theme\": [\"Winter Solace\", \"Christmas Cheer\"],\n" +
            "    \"image\": \"https://www.thespruce.com/thmb/uKbhHG8NrCJKLzrNZVGsMJSD2S4=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/grow-philodendron-houseplants-1902768-04-8bee1496f67e41c392304329eaea505e.jpg\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"en\": \"Boston Fern\",\n" +
            "    \"ko\": \"보스턴 고사리\",\n" +
            "    \"voice\": [\"Classic\", \"Friendly\", \"Welcoming\"],\n" +
            "    \"theme\": [\"Winter Solace\", \"Spring Renewal\"],\n" +
            "    \"image\": \"https://www.bhg.com/thmb/cZ04floUwhG4zys7vHZw8p8K-ao=/1244x0/filters:no_upscale():strip_icc()/boston-fern-660cc6ba-eab2a0ff4c534606b4f4590e47b18a11.jpg\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"en\": \"Chinese Evergreen\",\n" +
            "    \"ko\": \"중국 상록\",\n" +
            "    \"voice\": [\"Professional\", \"Calming\", \"Adaptable\"],\n" +
            "    \"theme\": [\"Spring Renewal\", \"Winter Wonderland\"],\n" +
            "    \"image\": \"https://www.gardendesign.com/pictures/images/275x295Exact_16x0/site_3/chinese-evergreen-plant-aglaonema-shutterstock-com_15962.jpg\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"en\": \"ZZ Plant\",\n" +
            "    \"ko\": \"ZZ 플랜트\",\n" +
            "    \"voice\": [\"Minimalist\", \"Resilient\", \"Professional\"],\n" +
            "    \"theme\": [\"Winter Wonderland\", \"New Year Fresh Start\"],\n" +
            "    \"keyword\": [\"Low Light Tolerant\"],\n" +
            "    \"image\": \"https://glasswingshop.com/cdn/shop/products/8D2A2069_2048x2048.jpg?v=1595400475\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"en\": \"Orchid\",\n" +
            "    \"ko\": \"난초\",\n" +
            "    \"voice\": [\"Sophisticated\", \"Elegant\", \"Playful\"],\n" +
            "    \"theme\": [\"Valentine's Romance\", \"Summer Vibrance\"],\n" +
            "    \"image\": \"https://www.ikea.com/kr/en/images/products/fejka-artificial-potted-plant-orchid-white__0748880_pe745269_s5.jpg?f=s\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"en\": \"Pothos\",\n" +
            "    \"ko\": \"포토스\",\n" +
            "    \"voice\": [\"Friendly\", \"Adaptable\", \"Playful\"],\n" +
            "    \"theme\": [\"Summer Vibrance\", \"Winter Solace\"],\n" +
            "    \"image\": \"https://www.thespruce.com/thmb/0QHhioSY7xteAdwlANsMQKLevOM=/3000x0/filters:no_upscale():max_bytes(150000):strip_icc()/how-to-make-pothos-fuller-5324082-hero-57b72aa5638a4653a938b6130d555dac.jpg\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"en\": \"Jade Plant\",\n" +
            "    \"ko\": \"옥 장미\",\n" +
            "    \"voice\": [\"Classic\", \"Confident\", \"Minimalist\"],\n" +
            "    \"theme\": [\"Autumn Harvest\", \"Winter Wonderland\"],\n" +
            "    \"image\": \"https://hips.hearstapps.com/hmg-prod/images/jade-plant-1-645157c445e14.jpg?crop=1.00xw:0.829xh;0,0&resize=1200:*\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"en\": \"Monstera\",\n" +
            "    \"ko\": \"몬스테라\",\n" +
            "    \"voice\": [\"Trendy\", \"Bold\", \"Architectural\"],\n" +
            "    \"theme\": [\"New Year Fresh Start\", \"Autumn Harvest\"],\n" +
            "    \"image\": \"https://images.squarespace-cdn.com/content/v1/56923fa6a976af0bfc533475/4487beac-be01-4ad5-8133-3276fb81972b/IMG_7938.jpg\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"en\": \"Bamboo Palm\",\n" +
            "    \"ko\": \"대나무 야자\",\n" +
            "    \"voice\": [\"Calming\", \"Soothing\", \"Energetic\"],\n" +
            "    \"theme\": [\"Spring Renewal\", \"Summer Vibrance\"],\n" +
            "    \"image\": \"https://bloomscape.com/wp-content/uploads/2020/08/bloomscape_bamboo-palm_stone-1.jpg?ver=279689\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"en\": \"Areca Palm\",\n" +
            "    \"ko\": \"아레카 야자\",\n" +
            "    \"voice\": [\"Welcoming\", \"Sophisticated\", \"Energetic\"],\n" +
            "    \"theme\": [\"Summer Vibrance\", \"Valentine's Romance\"],\n" +
            "    \"image\": \"https://budsnblush.com/cdn/shop/files/pl_15___01.jpg?v=1686196705\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"en\": \"Yucca\",\n" +
            "    \"ko\": \"유카\",\n" +
            "    \"voice\": [\"Bold\", \"Architectural\", \"Confident\"],\n" +
            "    \"theme\": [\"Autumn Harvest\", \"New Year Fresh Start\"],\n" +
            "    \"image\": \"https://upload.wikimedia.org/wikipedia/commons/thumb/5/56/Yucca_filamentosa.jpg/220px-Yucca_filamentosa.jpg\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"en\": \"Dracaena\",\n" +
            "    \"ko\": \"드라세나\",\n" +
            "    \"voice\": [\"Sophisticated\", \"Bold\", \"Trendy\"],\n" +
            "    \"theme\": [\"Autumn Harvest\", \"New Year Fresh Start\"],\n" +
            "    \"image\": \"https://www.bhg.com/thmb/6C8s4WEOURnfiX2szudRgVYtPyI=/1500x0/filters:no_upscale():strip_icc()/dracaena-houseplant-01-hero--68f36341781e404eb08d61587ac9bf3d.jpg\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"en\": \"Schefflera\",\n" +
            "    \"ko\": \"세플레라\",\n" +
            "    \"voice\": [\"Adaptable\", \"Friendly\", \"Playful\"],\n" +
            "    \"theme\": [\"Winter Wonderland\", \"Spring Renewal\"],\n" +
            "    \"image\": \"https://www.thespruce.com/thmb/QECPunXpbAAdj_BvXai_PDUQWtw=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/KaraRileySchefflera-RECIRC-cdce731067d449d4b4a17e8505ef9f24.jpg\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"en\": \"Croton\",\n" +
            "    \"ko\": \"크로톤\",\n" +
            "    \"voice\": [\"Energetic\", \"Playful\", \"Bold\"],\n" +
            "    \"theme\": [\"Summer Vibrance\", \"Autumn Harvest\"],\n" +
            "    \"image\": \"https://www.bhg.com/thmb/xlAo9gUTfHLK3uPETcTZ_KJUy1I=/1244x0/filters:no_upscale():strip_icc()/codiaeum-croton-houseplant-b094548b-1a330870f0174922b7dce1d63945921b.jpg\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"en\": \"Bird of Paradise\",\n" +
            "    \"ko\": \"천국의 새\",\n" +
            "    \"voice\": [\"Sophisticated\", \"Elegant\", \"Artistic\"],\n" +
            "    \"theme\": [\"Valentine's Romance\", \"Summer Vibrance\"],\n" +
            "    \"image\": \"https://images.immediate.co.uk/production/volatile/sites/10/2021/04/2048x1365-Strelitzia-reginae-GettyImages-1270647929-4f76714.jpg?quality=90&resize=940,627\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"en\": \"Anthurium\",\n" +
            "    \"ko\": \"안스리움\",\n" +
            "    \"voice\": [\"Playful\", \"Elegant\", \"Chic\"],\n" +
            "    \"theme\": [\"Valentine's Romance\", \"Christmas Cheer\"],\n" +
            "    \"image\": \"https://hgtvhome.sndimg.com/content/dam/images/hgtv/fullset/2022/3/10/1/shutterstock_108525305.jpg.rend.hgtvcom.1280.960.suffix/1646960174968.jpeg\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"en\": \"Calathea\",\n" +
            "    \"ko\": \"칼라테아\",\n" +
            "    \"voice\": [\"Artistic\", \"Sophisticated\", \"Welcoming\"],\n" +
            "    \"theme\": [\"Spring Renewal\", \"Christmas Cheer\"],\n" +
            "    \"image\": \"https://n1gardencentre.co.uk/wp-content/uploads/2022/03/Calathea-makoyana-2-scaled.jpg\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"en\": \"Maidenhair Fern\",\n" +
            "    \"ko\": \"메이든헤어 고사리\",\n" +
            "    \"voice\": [\"Elegant\", \"Delicate\", \"Sophisticated\", \"Calming\"],\n" +
            "    \"theme\": [\"Spring Renewal\", \"Valentine's Romance\"],\n" +
            "    \"keyword\": [\"High Humidity Thrivers\"],\n" +
            "    \"image\": \"https://images.squarespace-cdn.com/content/v1/640b619e6afd387f45e09638/d6398be6-ab18-4516-9f26-c5fe4133c20f/How+To+Grow+%26+Care+For+Maidenhair+Fern+%28adiantum+pedantum%29+by+Blacklotus+Landscaping.jpg\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"en\": \"Cast Iron Plant\",\n" +
            "    \"ko\": \"무쇠 잎\",\n" +
            "    \"voice\": [\"Adaptable\", \"Tough\", \"Minimalist\", \"Classic\"],\n" +
            "    \"theme\": [\"Winter Wonderland\", \"Autumn Harvest\"],\n" +
            "    \"keyword\": [\"Low Light Tolerant\"],\n" +
            "    \"image\": \"https://www.thespruce.com/thmb/I4XSZJP1cIOIoqJdheihOVG2i7w=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/grow-cast-iron-plants-aspidistra-1902740-7-b73a4e6f33a84b04aba7303169f4cb9e.jpg\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"en\": \"Aglaonema\",\n" +
            "    \"ko\": \"아글라오네마\",\n" +
            "    \"voice\": [\"Bold\", \"Confident\", \"Sophisticated\", \"Chic\"],\n" +
            "    \"theme\": [\"Autumn Harvest\", \"Christmas Cheer\"],\n" +
            "    \"image\": \"https://www.thegrowcentre.com.au/cdn/shop/products/ladyvalentinewithoutlogo_2000x.png?v=1659416973\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"en\": \"Ivy\",\n" +
            "    \"ko\": \"아이비\",\n" +
            "    \"voice\": [\"Classic\", \"Energetic\", \"Architectural\", \"Adaptable\"],\n" +
            "    \"theme\": [\"Winter Wonderland\", \"Spring Renewal\"],\n" +
            "    \"image\": \"https://www.thespruce.com/thmb/gcVaeZSb2lyNe4FaFSpRoG4OCl8=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/Persian-ivy-big-db91835bc43b4382a0ac5c8a2b5cdde7.jpg\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"en\": \"Parlor Palm\",\n" +
            "    \"ko\": \"거실 야자\",\n" +
            "    \"voice\": [\"Elegant\", \"Sophisticated\", \"Welcoming\", \"Classic\"],\n" +
            "    \"theme\": [\"Valentine's Romance\", \"Winter Solace\"],\n" +
            "    \"image\": \"https://www.dahingplants.com/cdn/shop/products/detailSSP_3061_1400x.jpg?v=1644879048\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"en\": \"String of Pearls\",\n" +
            "    \"ko\": \"진주 목걸이\",\n" +
            "    \"voice\": [\"Playful\", \"Artistic\", \"Trendy\", \"Chic\"],\n" +
            "    \"theme\": [\"New Year Fresh Start\", \"Christmas Cheer\"],\n" +
            "    \"keyword\": [\"Hanging Varieties\"],\n" +
            "    \"image\": \"https://gardengoodsdirect.com/cdn/shop/files/string-of-pearls-plant-30347238998058_x560.progressive.jpg?v=1695445618\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"en\": \"Prayer Plant\",\n" +
            "    \"ko\": \"기도하는 식물\",\n" +
            "    \"voice\": [\"Energetic\", \"Playful\", \"Friendly\", \"Bold\"],\n" +
            "    \"theme\": [\"Summer Vibrance\", \"Spring Renewal\"],\n" +
            "    \"keyword\": [\"High Humidity Thrivers\"],\n" +
            "    \"image\": \"https://www.thespruce.com/thmb/HxyHBC1R6BVZr0AXZvXDC4oAyLA=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/grow-maranta-inside-1902647-02-de777eb8e7804c3d86d658cd242583d6.jpg\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"en\": \"Asparagus Fern\",\n" +
            "    \"ko\": \"아스파라거스 고사리\",\n" +
            "    \"voice\": [\"Playful\", \"Adaptable\", \"Friendly\", \"Calming\"],\n" +
            "    \"theme\": [\"Spring Renewal\", \"Winter Wonderland\"],\n" +
            "    \"keyword\": [\"High Humidity Thrivers\"],\n" +
            "    \"image\": \"https://images.immediate.co.uk/production/volatile/sites/10/2021/06/2048x1365-Asparagus-fern-SEO-GettyImages-1220176700-9aa2276.jpg?quality=90&resize=940,627\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"en\": \"Dieffenbachia\",\n" +
            "    \"ko\": \"디펜바키아\",\n" +
            "    \"voice\": [\"Bold\", \"Confident\", \"Architectural\", \"Sophisticated\"],\n" +
            "    \"theme\": [\"Autumn Harvest\", \"New Year Fresh Start\"],\n" +
            "    \"image\": \"https://bloomscape.com/wp-content/uploads/2022/02/bloomscape_dieffenbachia_10in_clay-scaled.jpg?ver=675709\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"en\": \"Heartleaf Philodendron\",\n" +
            "    \"ko\": \"하트리프 필로덴드론\",\n" +
            "    \"voice\": [\"Welcoming\", \"Friendly\", \"Adaptable\", \"Classic\"],\n" +
            "    \"theme\": [\"Winter Solace\", \"Spring Renewal\"],\n" +
            "    \"image\": \"https://www.thespruce.com/thmb/YEGERwuv5RfMiGGG9TSdl_EAVxk=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/heartleaf-philodendron-guide-5181702-hero-d7f06d3be69a42e2b71441e6d0d0ed86.jpg\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"en\": \"Swiss Cheese Plant\",\n" +
            "    \"ko\": \"스위스 치즈 플랜트\",\n" +
            "    \"voice\": [\"Trendy\", \"Architectural\", \"Bold\", \"Chic\"],\n" +
            "    \"theme\": [\"New Year Fresh Start\", \"Autumn Harvest\"],\n" +
            "    \"image\": \"https://nouveauraw.com/wp-content/uploads/2020/10/swiss-cheese-plant-moss-pole-feature.png\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"en\": \"Kentia Palm\",\n" +
            "    \"ko\": \"켄티아 야자\",\n" +
            "    \"voice\": [\"Elegant\", \"Sophisticated\", \"Classic\", \"Welcoming\"],\n" +
            "    \"theme\": [\"Valentine's Romance\", \"Winter Solace\"],\n" +
            "    \"image\": \"https://mickeysplants.com/cdn/shop/products/IMG_1615_3024x.jpg?v=1594314288\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"en\": \"English Ivy\",\n" +
            "    \"ko\": \"잉글리시 아이비\",\n" +
            "    \"voice\": [\"Classic\", \"Energetic\", \"Sophisticated\", \"Architectural\"],\n" +
            "    \"theme\": [\"Winter Wonderland\", \"Spring Renewal\"],\n" +
            "    \"keyword\": [\"Hanging Varieties\"],\n" +
            "    \"image\": \"https://images.immediate.co.uk/production/volatile/sites/10/2021/09/hedera.helix_-02ba5c9.jpg?quality=90&resize=940,627\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"en\": \"Lucky Bamboo\",\n" +
            "    \"ko\": \"행운의 대나무\",\n" +
            "    \"voice\": [\"Friendly\", \"Welcoming\", \"Playful\", \"Energetic\"],\n" +
            "    \"theme\": [\"Spring Renewal\", \"Summer Vibrance\"],\n" +
            "    \"keyword\": [\"Feng Shui Plants\"],\n" +
            "    \"image\": \"https://www.ikea.com/gb/en/images/products/dracaena-plant-lucky-bamboo-spiral__1176176_pe895560_s5.jpg\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"en\": \"Cactus\",\n" +
            "    \"ko\": \"선인장\",\n" +
            "    \"voice\": [\"Bold\", \"Minimalist\", \"Tough\", \"Adaptable\"],\n" +
            "    \"theme\": [\"Autumn Harvest\", \"Winter Wonderland\"],\n" +
            "    \"keyword\": [\"Drought-Resistant\"],\n" +
            "    \"image\": \"https://dsk4t6ov5vq8n.cloudfront.net/uploads/PBS-Articles/2022/The-Green-Planet/Episode-4-photos/Sized-photos/The_Green_Planet_04_004.jpg\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"en\": \"Ponytail Palm\",\n" +
            "    \"ko\": \"포니테일 야자\",\n" +
            "    \"voice\": [\"Playful\", \"Trendy\", \"Friendly\", \"Adaptable\"],\n" +
            "    \"theme\": [\"Summer Vibrance\", \"New Year Fresh Start\"],\n" +
            "    \"keyword\": [\"Feng Shui Plants\", \"Drought-Resistant\"],\n" +
            "    \"image\": \"https://www.bhg.com/thmb/5h-tLrWSxSq5WOJM36eAiUwSOK0=/1280x0/filters:no_upscale():strip_icc()/ponytail-palm-plant-95d1052f-5225efca51f94e66b0d1d66bbbe7a997.jpg\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"en\": \"Radiator Plant\",\n" +
            "    \"ko\": \"라디에이터 플랜트\",\n" +
            "    \"voice\": [\"Chic\", \"Trendy\", \"Minimalist\", \"Architectural\"],\n" +
            "    \"theme\": [\"New Year Fresh Start\", \"Winter Wonderland\"],\n" +
            "    \"keyword\": [\"Feng Shui Plants\"],\n" +
            "    \"image\": \"https://www.southernliving.com/thmb/BEZcDKX8-tQMZhndEXlMrA9cz1o=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/gettyimages-1066941116-2000-7bd679aac31e402c8730a8e8de1b87ae.jpg\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"en\": \"African Violet\",\n" +
            "    \"ko\": \"아프리칸 바이올렛\",\n" +
            "    \"voice\": [\"Elegant\", \"Sophisticated\", \"Welcoming\", \"Calming\"],\n" +
            "    \"theme\": [\"Valentine's Romance\", \"Spring Renewal\"],\n" +
            "    \"keyword\": [\"Colorful Foliage\"],\n" +
            "    \"image\": \"https://www.almanac.com/sites/default/files/styles/large/public/image_nodes/african%20violet.jpg?itok=_kt-3Hsj\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"en\": \"Hoya\",\n" +
            "    \"ko\": \"호야\",\n" +
            "    \"voice\": [\"Artistic\", \"Trendy\", \"Chic\", \"Sophisticated\"],\n" +
            "    \"theme\": [\"Christmas Cheer\", \"New Year Fresh Start\"],\n" +
            "    \"keyword\": [\"Colorful Foliage\"],\n" +
            "    \"image\": \"https://cdn.shopify.com/s/files/1/0662/5489/files/hoya-wax-plant-cultivars.jpg?v=1572647635\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"en\": \"Ficus\",\n" +
            "    \"ko\": \"무화과나무\",\n" +
            "    \"voice\": [\"Classic\", \"Sophisticated\", \"Bold\", \"Architectural\"],\n" +
            "    \"theme\": [\"Autumn Harvest\", \"Winter Wonderland\"],\n" +
            "    \"keyword\": [\"Colorful Foliage\"],\n" +
            "    \"image\": \"https://nurserylive.com/cdn/shop/products/nurserylive-g-ficus-benjamina-weeping-fig-plant-204240_512x512.jpg?v=1679750033\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"en\": \"Poinsettia\",\n" +
            "    \"ko\": \"포인세티아\",\n" +
            "    \"voice\": [\"Bold\", \"Energetic\", \"Festive\", \"Confident\"],\n" +
            "    \"theme\": [\"Christmas Cheer\", \"Winter Solace\"],\n" +
            "    \"image\": \"https://cdn.britannica.com/08/120808-050-33E15F55/Poinsettia.jpg\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"en\": \"Christmas Cactus\",\n" +
            "    \"ko\": \"게발선인장\",\n" +
            "    \"voice\": [\"Playful\", \"Friendly\", \"Festive\", \"Chic\"],\n" +
            "    \"theme\": [\"Christmas Cheer\", \"Winter Solace\"],\n" +
            "    \"image\": \"https://www.whiteflowerfarm.com/mas_assets/cache/image/7/e/9/f/32415.Jpg\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"en\": \"Norfolk Island pine\",\n" +
            "    \"ko\": \"아라우카리아\",\n" +
            "    \"voice\": [\"Classic\", \"Elegant\", \"Sophisticated\", \"Bold\"],\n" +
            "    \"theme\": [\"Winter Solace\", \"Valentine's Romance\"],\n" +
            "    \"image\": \"https://www.thespruce.com/thmb/kAiGSBfLfRwCE_1a_jkyutMp22o=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/How-to-Grow-Norfolk-Island-Pine-Indoors-1crop-93cc3a88082f4e359e8a115c03b61d12.jpg\"\n" +
            "  }\n" +
            "]\n";

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    PlantRepository plantRepository;

    @Autowired
    PlantSpaceConditionRepository plantSpaceConditionRepository;

    @Autowired
    PlantThemeRepository plantThemeRepository;

    @Autowired
    PlantVoiceRepository plantVoiceRepository;

    @Autowired
    GuideThemeRepository guideThemeRepository;

    @Autowired
    GuideVoiceAndToneRepository guideVoiceAndToneRepository;

    @Autowired
    GuideSpaceConditionRepository guideSpaceConditionRepository;

    @Autowired
    VoiceAndToneRepository voiceAndToneRepository;

    @Autowired
    SpaceConditionRepository spaceConditionRepository;

    @Autowired
    ThemeRepository themeRepository;

//    @Disabled
    @Test
    void insert() throws JsonProcessingException {
        final int[] prices = {10000, 14000, 15000, 18000, 24000, 26000, 33000};

        PlantDto[] plantDtos = objectMapper.readValue(JSON, PlantDto[].class);
        System.out.println(Arrays.deepToString(plantDtos));

        for (PlantDto plantDto : plantDtos) {
            int randomInt = ThreadLocalRandom.current().nextInt(prices.length);
            Plant plant = plantRepository.findByName(plantDto.en)
                    .orElse(plantRepository.save(new Plant(plantDto.en, prices[randomInt], plantDto.voice.get(0), plantDto.image)));

            for (String v : plantDto.voice) {
                voiceAndToneRepository.findByValue(v)
                        .ifPresent(vt -> plantVoiceRepository.save(new PlantVoiceAndTone(plant, vt)));
            }

            for (String t : plantDto.theme) {
                themeRepository.findByValue(t)
                        .ifPresent(th -> plantThemeRepository.save(new PlantTheme(plant, th)));
            }

            if (plantDto.keyword != null) {
                for (String k : plantDto.keyword) {
                    spaceConditionRepository.findByValue(k)
                            .ifPresent(sc -> plantSpaceConditionRepository.save(new PlantSpaceCondition(plant, sc)));
                }
            }

            plantDto.voice
                    .stream()
                    .map(voiceAndToneRepository::findByValue)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .map((voiceAndTone -> new PlantVoiceAndTone(plant, voiceAndTone)))
                    .forEach((gv) -> guideVoiceAndToneRepository.save(gv));

            if (plantDto.keyword != null) {
                plantDto.keyword
                        .stream()
                        .map(spaceConditionRepository::findByValue)
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .map((k) -> new PlantSpaceCondition(plant, k))
                        .forEach((gs) -> guideSpaceConditionRepository.save(gs));
            }

            plantDto.theme
                    .stream()
                    .map(themeRepository::findByValue)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .map((theme) -> new PlantTheme(plant, theme))
                    .forEach((gt) -> guideThemeRepository.save(gt));
        }
    }

    record PlantDto(
            String en,
            String ko,
            String image,
            List<String> voice,
            List<String> theme,
            List<String> keyword
    ) {
    }
}
