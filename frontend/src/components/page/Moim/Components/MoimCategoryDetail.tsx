import styled from "@emotion/styled";
import { useEffect, useState } from "react";
import { GET, POST } from "../../../comon/axios/axiosInstance";
import { useMoimStore } from "../../../../types/State";

// -----------------------------
// ✅ Styled Components
// -----------------------------
const MoimContentContainer = styled.div`
  width: 70vw;
  max-width: 800px;
  background-color: #f0f4f8;
  padding: 50px;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  margin: 0 auto;
  display: flex;
  flex-direction: column;
  gap: 20px;
  align-items: center;
`;

const CategoryContent = styled.div`
  width: 100%;
  border: 1px solid #ccc;
  padding: 15px;
  border-radius: 6px;
  display: flex;
  flex-direction: column; /* 세로로 구성 */
  gap: 10px;
  background-color: white;
`;

const CategoryTop = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

const CategoryLabel = styled.label`
  flex: 1;
  font-size: 1.2rem;
  font-weight: 500;
  display: flex;
  flex-direction: column;
  justify-content: start;
  text-align: left;
`;

const CategoryDetailList = styled.div`
  margin-top: 8px;
  font-size: 0.9rem;
  color: #555;
`;

const DetailButtonWrapper = styled.div`
  display: flex;
  flex-wrap: wrap;
  gap: 8px;
  margin-top: 10px;
`;

const DetailButton = styled.button`
  background-color: #74b9ff;
  color: #fff;
  border: none;
  border-radius: 5px;
  padding: 6px 12px;
  cursor: pointer;
  transition: background-color 0.2s ease;
  &:hover {
    background-color: #0984e3;
  }
`;

// -----------------------------
// ✅ TypeScript Interfaces
// -----------------------------
interface MoimCategoryDetailType {
  MoimcategoryDetailId: string;
  MoimCategoryId: number;
  MoimcategorisationDetail: string;
}

interface Category {
  MoimCategoryId: number;
  categorisation: string;
  MoimCategoryDetail: MoimCategoryDetailType[];
}

// -----------------------------
// ✅ Main Component
// -----------------------------
const MoimCategoryDetail = () => {
  const [selectedCategory, setSelectedCategory] = useState<Category[]>([]);
  const [selectedCategoryId, setSelectedCategoryId] = useState<number | null>(
    null
  );
  const handleCategorySelect = (categoryId: string) => {
    setcategoryDetail(categoryId);
  };
  const { setcategoryDetail, moimData } = useMoimStore().MoimDataStore;

  // -----------------------------
  // ✅ 전체 카테고리 + 상세 불러오기
  // -----------------------------
  useEffect(() => {
    let cancelled = false;
    const fetchAll = async () => {
      try {
        const res = await GET({ url: "/category" });
        const categories: Category[] = Array.isArray(res.data) ? res.data : [];

        const detailPromises = categories.map(async (cat) => {
          try {
            const detailRes = await POST({
              url: `/CategoryDetail/get`,
              data: { MoimCategoryId: cat.MoimCategoryId },
            });
            return {
              ...cat,
              MoimCategoryDetail: Array.isArray(detailRes.data)
                ? detailRes.data
                : [],
            } as Category;
          } catch (err) {
            console.error(
              `Error fetching details for category ${cat.categorisation}:`,
              err
            );
            return { ...cat, MoimCategoryDetail: [] } as Category;
          }
        });

        const categoriesWithDetails = await Promise.all(detailPromises);

        if (!cancelled) {
          setSelectedCategory(categoriesWithDetails);
        }
      } catch (error) {
        console.error("Error fetching categories:", error);
        if (!cancelled) {
          setSelectedCategory([]);
        }
      }
    };

    fetchAll();

    return () => {
      cancelled = true;
    };
  }, []);

  useEffect(() => {
    console.log("Selected Category updated:", selectedCategory);
    console.log("Current Category Detail in Store:", moimData.categoryDetail);
  }, [moimData.categoryDetail, selectedCategory]);

  return (
    <MoimContentContainer>
      <div>모임 주제를 선택해볼까요?</div>

      {selectedCategory.map((category) => (
        <CategoryContent key={category.MoimCategoryId}>
          <CategoryTop>
            <CategoryLabel>
              <strong>{category.categorisation}</strong>
              <CategoryDetailList>
                {selectedCategoryId !== category.MoimCategoryId &&
                category.MoimCategoryDetail?.length > 0
                  ? category.MoimCategoryDetail.map((d, idx) => (
                      <span key={d.MoimcategoryDetailId}>
                        {d.MoimcategorisationDetail}
                        {idx !== category.MoimCategoryDetail.length - 1 && ", "}
                      </span>
                    ))
                  : null}
              </CategoryDetailList>
            </CategoryLabel>

            <input
              type="radio"
              name="category"
              value={category.MoimCategoryId}
              aria-label={category.categorisation}
              onClick={() => setSelectedCategoryId(category.MoimCategoryId)}
            />
          </CategoryTop>

          {selectedCategoryId === category.MoimCategoryId && (
            <DetailButtonWrapper>
              {category.MoimCategoryDetail.map((detail) => (
                <DetailButton
                  key={detail.MoimcategoryDetailId}
                  onClick={() =>
                    handleCategorySelect(detail.MoimcategoryDetailId)
                  }
                >
                  {detail.MoimcategorisationDetail}
                </DetailButton>
              ))}
            </DetailButtonWrapper>
          )}
        </CategoryContent>
      ))}
    </MoimContentContainer>
  );
};

export default MoimCategoryDetail;
