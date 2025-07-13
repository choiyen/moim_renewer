import { Helmet, HelmetData } from "react-helmet-async";

type HeadProps = {
  title?: string;
  description?: string;
};

const helmetData = new HelmetData({});

export const Head = ({ title = "", description = "" }: HeadProps = {}) => {
  return (
    <Helmet
      helmetData={helmetData}
      title={title ? `${title} | Moim 프로젝트` : undefined}
      defaultTitle="많은 사람들과 만나보아요? Moim에서...!"
    >
      <meta name="description" content={description} />
    </Helmet>
  );
};
