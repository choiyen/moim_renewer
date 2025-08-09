import styled from "@emotion/styled";
import React, { useState } from "react";
import { FaHeart, FaRegHeart } from "react-icons/fa";
import { useNavigate } from "react-router-dom";
import {
  History,
  LayoutGrid,
  Dumbbell,
  BookOpenCheck,
  MapPin,
} from "lucide-react";

const PageWrapper = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  background: linear-gradient(to bottom, #e0e7ff, #f8fafc);
  min-height: 100vh;
  padding-bottom: 40px;
`;

const SectionTitle = styled.h2`
  display: flex;
  gap: 10px;
  font-size: 24px;
  font-weight: 700;
  margin: 40px 0 20px;
  color: #1e293b;
  width: 80%;
  line-height: 1.5;
`;

const StyledSection = styled.div`
  text-align: center;
  background-color: #ffffff;
  padding: 30px 20px;
  border-radius: 12px;
  width: 90vw;
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.08);
  margin-bottom: 40px;
`;

const ScrollContainer = styled.div`
  overflow-x: auto;
  white-space: nowrap;
  padding: 10px;
`;

const InlineFlex = styled.div`
  display: inline-flex;
  gap: 20px;
  align-items: center;
  padding: 20px;
`;

const Card = styled.div`
  width: 230px;
  margin-right: 15px;
  flex-shrink: 0;
  white-space: wrap;
  background: #f1f5f9;
  border-radius: 10px;
  padding: 20px;
  text-align: left;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
`;

const CardImage = styled.img`
  width: 100%;
  height: 110px;
  object-fit: cover;
  border-radius: 8px;
`;

const CardTitle = styled.div`
  font-weight: 700;
  margin: 10px 0 4px;
`;

const CardDescription = styled.div`
  font-size: 14px;
  color: #475569;
  margin-bottom: 8px;
`;

const HeartIcon = styled.div`
  font-size: 20px;
  color: #ef4444;
  cursor: pointer;
  display: flex;
  justify-content: flex-end;
`;

type CardData = {
  id: number;
  image: string;
  title: string;
  maxpeople: number;
  Members: number;
  description: string;
  tag: string[];
};

const exampleCards: CardData[] = [
  {
    id: 1,
    image:
      "https://images.unsplash.com/photo-1525610553991-2bede1a236e2?auto=format&fit=crop&w=800&q=80",
    title: "스포츠 모임",
    maxpeople: 10,
    Members: 2,
    description: "함께 야구 경기 보실 분 구해요!",
    tag: ["스포츠", "야구"],
  },
  {
    id: 2,
    image:
      "https://images.unsplash.com/photo-1525610553991-2bede1a236e2?auto=format&fit=crop&w=800&q=80",
    title: "카페 모임",
    maxpeople: 6,
    Members: 2,
    description: "신상 카페 탐방 같이 가요 ☕",
    tag: ["카페", "탐방"],
  },
  {
    id: 3,
    image:
      "https://images.unsplash.com/photo-1525610553991-2bede1a236e2?auto=format&fit=crop&w=800&q=80",
    title: "등산 모임",
    maxpeople: 8,
    Members: 3,
    description: "북한산 정복할 멤버 모집 중!",
    tag: ["등산", "북한산"],
  },
  {
    id: 4,
    image:
      "https://images.unsplash.com/photo-1525610553991-2bede1a236e2?auto=format&fit=crop&w=800&q=80",
    title: "영화 모임",
    maxpeople: 5,
    Members: 2,
    description: "영화 같이 보실래요? 🎬",
    tag: ["영화", "관람"],
  },
  {
    id: 5,
    image:
      "https://images.unsplash.com/photo-1525610553991-2bede1a236e2?auto=format&fit=crop&w=800&q=80",
    title: "독서 모임",
    maxpeople: 5,
    Members: 2,
    description: "책을 같이 읽고 이야기 나눌 분 구해요! 📚",
    tag: ["독서", "책"],
  },
];
const JoinButton = styled.button`
  display: flex;
  align-items: flex-end;
  justify-content: flex-end;
  padding: 15px;
  background: none;
  border: none;
  font-size: 18px;
  color: #3b82f6;
  cursor: pointer;
  width: 100%;
  text-align: right;

  &:hover {
    text-decoration: underline;
  }
`;

const CardComponent: React.FC<{ data: CardData }> = ({ data }) => {
  const [liked, setLiked] = useState(false);
  const Navigate = useNavigate();

  const handleClick = () => {
    alert("페이지 이동합니다.");
    Navigate("/moim/detail/1");
  };

  return (
    <Card onClick={() => handleClick()}>
      <CardImage src={data.image} alt={data.title} />
      <CardTitle>{data.title}</CardTitle>
      <CardDescription>{data.description}</CardDescription>
      <CardDescription>
        {data.Members}/{data.maxpeople}
      </CardDescription>
      <CardDescription>
        {data.tag.map((tag, idx) => (
          <span
            key={idx}
            style={{
              marginRight: "6px",
              background: "#e0e7ff",
              borderRadius: "4px",
              padding: "2px 6px",
              fontSize: "12px",
            }}
          >
            #{tag}
          </span>
        ))}
      </CardDescription>
      <HeartIcon
        onClick={(e) => {
          e.stopPropagation();
          setLiked(!liked);
        }}
      >
        {liked ? <FaHeart /> : <FaRegHeart />}
      </HeartIcon>
    </Card>
  );
};

const Section: React.FC<{ title: string; icon: React.ReactNode }> = ({
  icon,
  title,
}) => (
  <>
    <SectionTitle>
      {icon}
      {title}
    </SectionTitle>
    <StyledSection>
      <ScrollContainer>
        <div style={{ textAlign: "right", marginRight: "15px" }}>
          더보기 {">"}
        </div>
        <InlineFlex>
          {exampleCards.map((item) => (
            <CardComponent key={item.id} data={item} />
          ))}
        </InlineFlex>
      </ScrollContainer>
    </StyledSection>
  </>
);

const MainPage = () => {
  const nativegate = useNavigate();

  return (
    <div>
      <PageWrapper>
        <Section icon={<History size={36} />} title="내가 접속한 모임 페이지" />
        <Section
          icon={<LayoutGrid size={36} />}
          title="다양한 모임을 만나볼까요?"
        />
        <Section
          icon={<Dumbbell size={36} />}
          title="스포츠를 좋아하신다고? 이런 건 어떠세요?"
        />
        <Section
          icon={<BookOpenCheck size={36} />}
          title="성장하고 싶다면 이런 모임은 어떠세요?"
        />
        <Section
          icon={<MapPin size={36} />}
          title="근처에서 활동 중인 모임이에요!"
        />
        <JoinButton onClick={() => nativegate("/moim/insert")}>
          마음에 드시는 모임이 없으신가요? 가입하러 가실래요? {">"}
        </JoinButton>
      </PageWrapper>
    </div>
  );
};

export default MainPage;
